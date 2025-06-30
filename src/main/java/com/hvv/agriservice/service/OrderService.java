package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.OrderDTO;
import com.hvv.agriservice.dto.model.OrderDetailDTO;
import com.hvv.agriservice.dto.model.OrderShowListDTO;
import com.hvv.agriservice.dto.request.CreateOrderRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OrderService {
    Mono<Boolean> createOrder(CreateOrderRequest request);
    Flux<OrderShowListDTO> findAllByPage(int page, int size);
    Mono<OrderDTO> findById(String id);
    Flux<OrderShowListDTO> getListToShow(int page, int size);
    Mono<OrderDetailDTO> getOrderDetailById(Long orderId);
    Mono<Boolean> updateOrderStatus(Long id, String status);
    Mono<List<String>> getListOrderStatus(String exclusionStatus);
}
