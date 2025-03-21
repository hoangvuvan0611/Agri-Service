package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.core.mapstruct.ProductMapper;
import com.hvv.agriservice.dto.model.ProductDTO;
import com.hvv.agriservice.dto.model.RecommendationIdProductDTO;
import com.hvv.agriservice.repository.ProductRepository;
import com.hvv.agriservice.service.ProductService;
import com.hvv.agriservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final RecommendationService recommendationService;

    private final ProductRepository productRepository;

    @Override
    public Flux<Long> getRecommendationsIdById(Long id) {
        return recommendationService.getRecommendationsByProductId(id)
                .map(RecommendationIdProductDTO::getId);
    }

    @Override
    public Flux<ProductDTO> getRecommendationsProductById(Long id) {
        return recommendationService.getRecommendationsByProductId(id)
                .map(RecommendationIdProductDTO::getId)
                .collectList()
                .filter(ids -> !ids.isEmpty())
                .flatMapMany(productRepository::getRecommendationsProductById);
    }

    @Override
    public Flux<ProductDTO> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllBy(pageable)
                .map(ProductMapper.INSTANCE::productToProductDTO);
    }

    @Override
    public Flux<ProductDTO> getProductsToShowInit(int page, int size) {
        return productRepository.getProductsToShowInit(page*size, size);
    }

    @Override
    public Mono<ProductDTO> getProductBySLug(String slug) {
        return productRepository.getProductBySlug(slug);
    }
}
