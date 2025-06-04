package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.config.common.SnowflakeIdGenerator;
import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import com.hvv.agriservice.core.mapstruct.OrderMapper;
import com.hvv.agriservice.dto.model.OrderDTO;
import com.hvv.agriservice.dto.model.OrderShowListDTO;
import com.hvv.agriservice.dto.model.ShippingInfo;
import com.hvv.agriservice.dto.request.CreateOrderRequest;
import com.hvv.agriservice.entity.Customer;
import com.hvv.agriservice.entity.Order;
import com.hvv.agriservice.entity.OrderItem;
import com.hvv.agriservice.repository.CustomerRepository;
import com.hvv.agriservice.repository.OrderItemRepository;
import com.hvv.agriservice.repository.OrderRepository;
import com.hvv.agriservice.service.OrderService;
import com.hvv.agriservice.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;
    private final OrderItemRepository orderItemRepository;

//    @Override
//    public Mono<Boolean> createOrder(CreateOrderRequest request) {
//        ShippingInfo shippingInfo = request.getShippingInfo();
//
//        Customer customerToSave = Customer.builder()
//                .id(snowflakeIdGenerator.generateId())
//                .username(shippingInfo.getFullName())
//                .email(shippingInfo.getEmail())
//                .phoneNumber(shippingInfo.getPhone())
//                .cityId(DataUtils.safeToLong(shippingInfo.getCity()))
//                .districtId(DataUtils.safeToLong(shippingInfo.getDistrict()))
//                .wardId(DataUtils.safeToLong(shippingInfo.getWard()))
//                .isNew(true)
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        return customerRepository.save(customerToSave)
//                .flatMap(customer -> {
//                    Order orderToSave = Order.builder()
//                            .id(snowflakeIdGenerator.generateId())
//                            .customerId(customer.getId())
//                            .status(OrderStatusEnum.PENDING)
//                            .totalFee(BigDecimal.valueOf(DataUtils.safeToLong(request.getTotalAmount())))
//                            .createdAt(LocalDateTime.now())
//                            .isNew(true)
//                            .build();
//                    List<OrderItem> orderItemList = new ArrayList<>();
//                    for (OrderItemDTO item: request.getOrderItemDTOList()) {
//                        orderItemList.add(
//                                OrderItem.builder()
//                                        .orderId(orderToSave.getId())
//                                        .id(snowflakeIdGenerator.generateId())
//                                        .quantity(item.getQuantity())
//                                        .productId(Long.valueOf(item.getId()))
//                                        .createdAt(LocalDateTime.now())
//                                        .build());
//                    }
//                    orderItemRepository.saveAll(orderItemList);
//                    return orderRepository.save(orderToSave)
//                            .thenReturn(true); // sau khi lưu order thành công
//                });
//    }
    @Override
    public Mono<Boolean> createOrder(CreateOrderRequest request) {
        ShippingInfo shippingInfo = request.getShippingInfo();
        return customerRepository
                .save(Customer.builder()
                        .id(snowflakeIdGenerator.generateId())
                        .username(shippingInfo.getFullName())
                        .email(shippingInfo.getEmail())
                        .phoneNumber(shippingInfo.getPhone())
                        .cityId(DataUtils.safeToLong(shippingInfo.getCity()))
                        .districtId(DataUtils.safeToLong(shippingInfo.getDistrict()))
                        .wardId(DataUtils.safeToLong(shippingInfo.getWard()))
                        .isNew(true)
                        .createdAt(LocalDateTime.now())
                        .build()
                )
                .flatMap(customer -> orderRepository
                        .save(Order.builder()
                                .id(snowflakeIdGenerator.generateId())
                                .customerId(customer.getId())
                                .status(OrderStatusEnum.PENDING)
                                .totalFee(BigDecimal.valueOf(DataUtils.safeToLong(request.getTotalAmount())))
                                .createdAt(LocalDateTime.now())
                                .isNew(true)
                                .build()
                        ).flatMap(order -> orderItemRepository
                                .saveAll(request.getOrderItemList().stream()
                                        .map(item -> OrderItem.builder()
                                                .orderId(order.getId())
                                                .name("")
                                                .id(snowflakeIdGenerator.generateId())
                                                .quantity(item.getQuantity())
                                                .productId(Long.valueOf(item.getId()))
                                                .createdAt(LocalDateTime.now())
                                                .isNew(true)
                                                .build()
                                        )
                                        .collect(Collectors.toList()))
                                .collectList().thenReturn(true)
                        )
                );
    }

    @Override
    public Flux<OrderDTO> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAllBy(pageable)
                .map(OrderMapper.INSTANCE::orderToOrderDTO);
    }

    @Override
    public Mono<OrderDTO> findById(String id) {
        return orderRepository.findById(DataUtils.safeToLong(id))
                .map(OrderMapper.INSTANCE::orderToOrderDTO);
    }

    @Override
    public Flux<OrderShowListDTO> getListToShow(int page, int size) {
        return orderRepository.getListToShow(page * size, size);
    }
}
