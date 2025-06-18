package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.ProductManagementDTO;
import reactor.core.publisher.Flux;

public interface ProductRepositoryCustom {
    Flux<ProductManagementDTO> findProductsByMultiCondition(
            int size,
            int offset,
            String keyword,
            Long categoryId
    );
}
