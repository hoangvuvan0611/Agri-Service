package com.hvv.agriservice.service;

import io.minio.messages.Bucket;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MinioService {
    Mono<Void> createBucketIfNotExists();

    /**
     * Tai file len minio
     * @param filePart
     * @return
     */
    Mono<String> uploadFile(FilePart filePart);

    /**
     * Tai file xuong tu Minio
     * @param fileName
     * @return
     */
    Mono<Flux<DataBuffer>> downloadFile(String fileName);

    /**
     * Lay url cua file
     * @param fileName
     * @return
     */
    Mono<String> getFileUrl(String fileName);

    Flux<Bucket> getAllBuckets();
}
