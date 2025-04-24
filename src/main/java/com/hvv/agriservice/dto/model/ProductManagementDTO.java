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
public class ProductManagementDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private String category;
    private Long quantity;
    private Long sold;
    private Long stock;
    private String status;
}
