package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.consts.Common.*;

@Slf4j
@RestController
@RequestMapping(path = ProductPath.PRODUCT_PATH)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public ResponseEntity<?> getAll() {return null;}

    @GetMapping(path = "/recommend_ids/{id}")
    public Mono<ResponseData<?>> getRecommendationsIdById(@PathVariable Long id) {
        return productService.getRecommendationsIdById(id)
                .collectList()
                .map(ids -> ResponseData.success("", ids));
    }

    @GetMapping(path = "/recommend_products/{id}")
    public Mono<ResponseData<?>> getRecommendationsProductById(@PathVariable Long id) {
        return productService.getRecommendationsProductById(id)
                .collectList()
                .map(productDTOS -> ResponseData.success("Thành công", productDTOS));
    }

    @PostMapping(path = CREATE)
    public ResponseEntity<?> create() {
        return null;
    }

    @PutMapping(path = UPDATE)
    public ResponseEntity<?> update() {
        return null;
    }
}
