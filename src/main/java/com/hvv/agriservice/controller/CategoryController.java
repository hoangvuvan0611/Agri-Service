package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = CategoryPath.CATEGORY_PATH)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public Mono<ResponseData<?>> getAll() {
        return categoryService.getAll()
                .collectList()
                .map(categoryDTOS -> ResponseData.success("", categoryDTOS));
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
