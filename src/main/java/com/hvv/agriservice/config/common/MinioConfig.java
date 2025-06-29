package com.hvv.agriservice.config.common;

import io.minio.MinioClient;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MinioConfig {
    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    Logger logger = LoggerFactory.getLogger(MinioConfig.class);

    @Bean
    public MinioClient initMinioClient() {
        try {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.MINUTES)
                    .build();

            return MinioClient.builder()
                    .endpoint(endpoint)
                    .httpClient(httpClient)
                    .credentials(accessKey, secretKey)
                    .build();
        } catch (Exception e) {
            logger.error("Error when calling external API", e);
            e.printStackTrace();
            logger.error("Init Minio error: {}", e.getMessage());
            throw new RuntimeException("IMC0000: Init minio error" + e.getMessage());
        }
    }
}
