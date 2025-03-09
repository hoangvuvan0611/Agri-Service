package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.ProductDTO;
import reactor.core.publisher.Flux;

public interface ProductService {

    Flux<Long> getRecommendationsIdById(Long id);
    Flux<ProductDTO> getRecommendationsProductById(Long id);
}
