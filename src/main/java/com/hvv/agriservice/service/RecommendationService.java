package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.RecommendationIdProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface RecommendationService {

    Mono<Map> checkRecommendationApiStatus();

    Flux<RecommendationIdProductDTO> getRecommendationsByProductId(Long id);
}
