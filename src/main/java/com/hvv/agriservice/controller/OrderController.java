package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.dto.request.CreateOrderRequest;
import com.hvv.agriservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping(path = OrderPath.ORDER_PATH)
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/{id}")
    public Mono<ResponseData<?>> getById(@PathVariable String id) {
        return orderService.findById(id)
                .map(orderDTO -> ResponseData.success("", orderDTO));
    }

    @GetMapping(path = ALL)
    public Mono<ResponseData<?>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return orderService.findAllByPage(page, size)
                .collectList()
                .map(orders -> ResponseData.success("", orders));
    }

    @PostMapping(path = CREATE)
    public Mono<ResponseData<?>> create(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request)
                .map(result -> ResponseData.success("", result));
    }

    @PutMapping(path = UPDATE)
    public ResponseEntity<?> update() {
        return null;
    }
}
