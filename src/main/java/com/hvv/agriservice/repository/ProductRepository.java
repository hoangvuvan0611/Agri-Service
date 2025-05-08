package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.ProductDTO;
import com.hvv.agriservice.dto.model.ProductManagementDTO;
import com.hvv.agriservice.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.hvv.agriservice.repository.CustomQuery.*;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long>, ReactiveSortingRepository<Product, Long> {
    Flux<Product> findByNameContainingIgnoreCase(String name);
    Mono<Product> findByNameIgnoreCase(String name);

    @Query(getRecommendationProductsByProductId)
    Flux<ProductDTO> getRecommendationsProductById(List<Long> ids);

    Flux<Product> findAllBy(Pageable pageable);

    Mono<Long> count();

    @Query(getProductsToShowInit)
    Flux<ProductDTO> getProductsToShowInit(int offset, int size);

    @Query(getProductBySlug)
    Mono<ProductDTO> getProductBySlug(String slug);

    @Query(getCountAllProduct)
    Mono<Long> getTotal();

    @Query(getProductToShowManagement)
    Flux<ProductManagementDTO> getProductToShowManagement(int offset, int size);

    @Query(searchByName)
    Flux<ProductDTO> searchProductByName(String keyword);
}
