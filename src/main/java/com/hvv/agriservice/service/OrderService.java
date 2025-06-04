package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.OrderDTO;
import com.hvv.agriservice.dto.model.OrderShowListDTO;
import com.hvv.agriservice.dto.request.CreateOrderRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Boolean> createOrder(CreateOrderRequest request);
    Flux<OrderDTO> findAllByPage(int page, int size);
    Mono<OrderDTO> findById(String id);
    Flux<OrderShowListDTO> getListToShow(int page, int size);
}
