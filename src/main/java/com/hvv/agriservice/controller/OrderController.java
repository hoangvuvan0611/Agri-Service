package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.dto.request.CreateOrderRequest;
import com.hvv.agriservice.service.OrderService;
import com.hvv.agriservice.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.*;
import static com.hvv.agriservice.constant.Const.OrderPath.ORDER_STATUS;
import static com.hvv.agriservice.constant.Const.OrderPath.UPDATE_STATUS;

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

    @GetMapping(path = SHOW_LIST)
    public Mono<ResponseData<?>> getListShow(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return orderService.getListToShow(page, size)
                .collectList()
                .map(orders -> ResponseData.success("", orders));
    }

    @GetMapping(path = DETAIL_ID)
    public Mono<ResponseData<?>> getOrderDetailById(@PathVariable String id) {
        return orderService.getOrderDetailById(DataUtils.safeToLong(id))
                .map(orderDTO -> ResponseData.success("", orderDTO));
    }

    @GetMapping(path = UPDATE_STATUS)
    public Mono<ResponseData<?>> updateOrderStatus(@RequestParam(name = "id") String id,
                                                   @RequestParam(name = "status") String status) {
      return orderService.updateOrderStatus(Long.valueOf(id), status)
              .map(result -> ResponseData.success("", result));
    }

    @GetMapping(path = ORDER_STATUS)
    public Mono<ResponseData<?>> getListOrderStatus(@RequestParam(name = "exclusionStatus") String exclusionStatus) {
        return orderService.getListOrderStatus(exclusionStatus)
                .map(result -> ResponseData.success("", result));
    }
}
