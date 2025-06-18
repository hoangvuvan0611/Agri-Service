package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.ProductDTO;
import com.hvv.agriservice.dto.model.ProductManagementDTO;
import com.hvv.agriservice.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Flux<Long> getRecommendationsIdById(Long id);
    Flux<ProductDTO> getRecommendationsProductById(Long id);

    Flux<ProductDTO> findAllByPage(int page, int size);
    Flux<ProductDTO> getProductsToShowInit(int page, int size);
    Mono<ProductDTO> getProductBySLug(String slug);
    Flux<ProductManagementDTO> getProductsByCategoryId(int page, int size, Long categoryId);
    Mono<Long> getTotal();
    Flux<ProductManagementDTO> getProductToShowManagement(int page, int size);
    Mono<Product> getProductById(String id);
    Flux<ProductDTO> searchByKeyword(String keyword);
    Flux<ProductDTO> getListProductBestSellerInMonth(int size);
    Flux<ProductManagementDTO> findProductsByMultiCondition(int size, int page, String keyword, Long categoryId);
    Flux<ProductDTO> findProductsByListId(List<Long> listProductId);
}
