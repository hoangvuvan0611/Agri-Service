package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.config.common.SnowflakeIdGenerator;
import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import com.hvv.agriservice.dto.model.ShippingInfo;
import com.hvv.agriservice.dto.request.CreateOrderRequest;
import com.hvv.agriservice.entity.Customer;
import com.hvv.agriservice.entity.Order;
import com.hvv.agriservice.repository.CustomerRepository;
import com.hvv.agriservice.repository.OrderRepository;
import com.hvv.agriservice.service.OrderService;
import com.hvv.agriservice.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public Mono<Boolean> createOrder(CreateOrderRequest request) {
        ShippingInfo shippingInfo = request.getShippingInfo();

        Customer customerToSave = Customer.builder()
                .id(snowflakeIdGenerator.generateId())
                .username(shippingInfo.getFullName())
                .email(shippingInfo.getEmail())
                .phoneNumber(shippingInfo.getPhone())
                .cityId(DataUtils.safeToLong(shippingInfo.getCity()))
                .districtId(DataUtils.safeToLong(shippingInfo.getDistrict()))
                .wardId(DataUtils.safeToLong(shippingInfo.getWard()))
                .isNew(true)
                .createdAt(LocalDateTime.now())
                .build();

        return customerRepository.save(customerToSave)
                .flatMap(customer -> {
                    Order orderToSave = Order.builder()
                            .id(snowflakeIdGenerator.generateId())
                            .customerId(customer.getId())
                            .status(OrderStatusEnum.PENDING)
                            .totalFee(BigDecimal.valueOf(DataUtils.safeToLong(request.getTotalAmount())))
                            .createdAt(LocalDateTime.now())
                            .isNew(true)
                            .build();
                    return orderRepository.save(orderToSave)
                            .thenReturn(true); // sau khi lưu order thành công
                });
    }
}
