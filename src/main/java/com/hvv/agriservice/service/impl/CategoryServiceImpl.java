package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.core.mapstruct.CategoryMapper;
import com.hvv.agriservice.dto.model.CategoryDTO;
import com.hvv.agriservice.repository.CategoryRepository;
import com.hvv.agriservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Flux<CategoryDTO> getAll() {
        return categoryRepository.findAll().map(CategoryMapper.INSTANCE::categoryToCategoryDTO);
    }
}
