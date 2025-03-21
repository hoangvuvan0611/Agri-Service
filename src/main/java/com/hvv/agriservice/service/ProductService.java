package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Long> getRecommendationsIdById(Long id);
    Flux<ProductDTO> getRecommendationsProductById(Long id);

    Flux<ProductDTO> findAllByPage(int page, int size);
    Flux<ProductDTO> getProductsToShowInit(int page, int size);
    Mono<ProductDTO> getProductBySLug(String slug);
}
