package com.hvv.agriservice.core.mapstruct;

import com.hvv.agriservice.dto.model.CategoryDTO;
import com.hvv.agriservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    public CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
    CategoryDTO categoryToCategoryDTO(Category category);
}
