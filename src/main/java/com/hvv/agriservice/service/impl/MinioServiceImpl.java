package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.service.MinioService;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    Logger log = LoggerFactory.getLogger(MinioServiceImpl.class);

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;
    @Value("${minio.endpoint}")
    private String endpoint;

    @Override
    public Mono<Void> createBucketIfNotExists() {
        return Mono.fromCallable(() -> {
            boolean bucketExists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build()
            );

            if (!bucketExists) {
                minioClient.makeBucket(
                    MakeBucketArgs.builder()
                        .bucket(bucketName)
                        .build()
                );
            }
            return Mono.empty();
        })
        .doOnError(e -> {
            log.error("Create bucket error: {}", e.getMessage());
            throw new RuntimeException("CBINE0001: Create bugket error: " + e.getMessage());
        })
        .then();
    }

    @Override
    public Mono<String> uploadFile(FilePart filePart) {
        return createBucketIfNotExists()
            .then(
                // Gop cac databuffer thanh buffer duy nhat
                DataBufferUtils.join(filePart.content())
            )
            .flatMap(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                DataBufferUtils.release(dataBuffer);
                // Dat ten file
                String fileName = System.currentTimeMillis() + "_" + filePart.filename();
                return Mono.fromCallable(() -> {
                    // Upload file to minio
                    try {
                        minioClient.putObject(
                            PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                                .build()
                        );
                        return fileName;
                    } catch (Exception e) {
                        throw new RuntimeException("UF0000-1: File upload failed", e);
                    }
                })
                .subscribeOn(Schedulers.boundedElastic());   // Tranh block luong chinh, dam bao khong dong bo khu upload
            });
    }

    @Override
    public Mono<Flux<DataBuffer>> downloadFile(String fileName) {
        return Mono.fromCallable(() -> {
            InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build()
            );
            return DataBufferUtils.readInputStream(
                () -> inputStream,
                new DefaultDataBufferFactory(),
                4096
            );
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> getPreSignedUrl(String fileName) {
        return Mono.fromCallable(() ->
            minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(fileName)
                    .expiry(24*60*60)   // Het han sau 24h
                    .build()
            )
        );
    }

    @Override
    public Mono<String> getDirectObjectUrl(String objectName) {
        return Mono.fromCallable(() -> String.format("%s/%s/%s", endpoint, bucketName, objectName));
    }

    @Override
    public Flux<Bucket> getAllBuckets() {
        return null;
    }
}
