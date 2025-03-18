package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.ProductDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;

public interface ProductService {

    Flux<Long> getRecommendationsIdById(Long id);
    Flux<ProductDTO> getRecommendationsProductById(Long id);

    Flux<ProductDTO> findAllByPage(int page, int size);
    Flux<ProductDTO> getProductsToShowInit(int page, int size);
}
