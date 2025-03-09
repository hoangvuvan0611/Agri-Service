package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.ProductDTO;
import com.hvv.agriservice.entity.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.hvv.agriservice.repository.CustomQuery.getRecommendationProductsByProductId;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByNameContainingIgnoreCase(String name);
    Mono<Product> findByNameIgnoreCase(String name);

    @Query(getRecommendationProductsByProductId)
    Flux<ProductDTO> getRecommendationsProductById(List<Long> ids);
}
