package com.hvv.agriservice.config.web;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${webClient.recommendation.url}")
    private String recommendationServiceUrl;

    @Bean
    @Qualifier("recommendationWebClient")
    public WebClient recommendationWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(recommendationServiceUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
