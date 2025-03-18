package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.consts.Common.*;
import static com.hvv.agriservice.constant.consts.Common.ProductPath.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = PRODUCT_PATH)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Tao moi san pham
     * @return
     */
    @PostMapping(path = CREATE)
    public ResponseEntity<?> create() {
        return null;
    }

    /**
     * Sua thong tin san pham
     * @return
     */
    @PutMapping(path = UPDATE)
    public ResponseEntity<?> update() {
        return null;
    }

    /**
     * Lay san pham theo id
     * @param id
     * @return
     */
    @GetMapping(path = ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    /**
     * Lay danh sach san pham
     * @return
     */
    @GetMapping(path = ALL)
    public Mono<ResponseData<?>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return productService.findAllByPage(page, size)
                .collectList()
                .map(productDTOS -> ResponseData.success("", productDTOS));
    }

    /**
     * Lay dang sach id cua san pham tuong tu cua 1 san pham theo id
     * @param id
     * @return
     */
    @GetMapping(path = GET_RECOMMENDATION_IDS_BY_ID)
    public Mono<ResponseData<?>> getRecommendationsIdById(@PathVariable Long id) {
        return productService.getRecommendationsIdById(id)
                .collectList()
                .map(ids -> ResponseData.success("", ids));
    }

    /**
     * Lay danh sach san pham tuong tu cua 1 san pham theo id
     * @param id
     * @return
     */
    @GetMapping(path = GET_RECOMMENDATION_PRODUCTS_BY_ID)
    public Mono<ResponseData<?>> getRecommendationsProductById(@PathVariable Long id) {
        return productService.getRecommendationsProductById(id)
                .collectList()
                .map(productDTOS -> ResponseData.success("Thành công", productDTOS));
    }

    /**
     * Ham goi lay danh sach san pham de show o trang chu
     * @return
     */
    @GetMapping("/showInit")
    public Mono<ResponseData<?>> getProductsToShowInit(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return productService.getProductsToShowInit(page, size)
                .collectList()
                .map(productDTOS -> ResponseData.success("", productDTOS));
    }
}
