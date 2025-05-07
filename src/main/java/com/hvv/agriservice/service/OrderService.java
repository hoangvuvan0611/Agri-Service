package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.request.CreateOrderRequest;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Boolean> createOrder(CreateOrderRequest request);
}
