package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.CategoryDTO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CategoryService {
    Flux<CategoryDTO> getAll();
}
