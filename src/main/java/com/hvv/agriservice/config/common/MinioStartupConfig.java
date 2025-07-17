package com.hvv.agriservice.config.common;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MinioStartupConfig implements CommandLineRunner {

//    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public void run(String... args) throws Exception {
        // Kiểm tra xem bucket có tồn tại chưa
//        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
//        if (!bucketExists) {
//            // Tạo bucket nếu chưa tồn tại
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
//        }

        // Áp dụng chính sách đọc công khai cho bucket
        String publicPolicy = "{\n" +
                "    \"Version\": \"2012-10-17\",\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": \"*\",\n" +
                "            \"Action\": [\"s3:GetObject\"],\n" +
                "            \"Resource\": [\"arn:aws:s3:::" + bucketName + "/*\"]\n" +
                "        }\n" +
                "    ]\n" +
                "}";

//        minioClient.setBucketPolicy(
//                SetBucketPolicyArgs.builder()
//                        .bucket(bucketName)
//                        .config(publicPolicy)
//                        .build()
//        );
    }
}
