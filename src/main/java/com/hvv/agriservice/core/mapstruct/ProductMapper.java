package com.hvv.agriservice.core.mapstruct;

import com.hvv.agriservice.dto.model.ProductDTO;
import com.hvv.agriservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    public ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
}
