package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String slug;
    private String uses;
    private String path;
    private String description;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private Long quantity;
    private Long sold;
    private Boolean featured;
}
