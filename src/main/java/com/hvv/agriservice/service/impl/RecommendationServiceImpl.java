package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.dto.model.RecommendationIdProductDTO;
import com.hvv.agriservice.service.RecommendationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final WebClient webClient;

    Logger log = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    public RecommendationServiceImpl(@Qualifier("recommendationWebClient") WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public Mono<Map> checkRecommendationApiStatus() {
        return null;
    }

    @Override
    public Flux<RecommendationIdProductDTO> getRecommendationsByProductId(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/recommend-by-id/{id}")
                        .build(id))
                .retrieve()
                .bodyToFlux(RecommendationIdProductDTO.class);
    }
}
