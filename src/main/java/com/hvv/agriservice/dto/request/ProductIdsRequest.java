package com.hvv.agriservice.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ProductIdsRequest {
    List<Long> listProductId;
}
