package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.OrderDetailDTO;
import reactor.core.publisher.Mono;

public interface OrderCustomRepository {
    Mono<OrderDetailDTO> getOrderDetailById(Long orderId);
}
