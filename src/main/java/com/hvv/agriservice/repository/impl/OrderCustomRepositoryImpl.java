package com.hvv.agriservice.repository.impl;

import com.hvv.agriservice.dto.model.CustomerDTO;
import com.hvv.agriservice.dto.model.OrderDetailDTO;
import com.hvv.agriservice.dto.model.OrderItemDTO;
import com.hvv.agriservice.repository.OrderCustomRepository;
import com.hvv.agriservice.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Mono<OrderDetailDTO> getOrderDetailById(Long orderId) {
        String sqlStr = " SELECT o.id, o.status, o.total_fee, o.shipping_fee, o.created_at, o.updated_at, " +
                " c.id, c.username as customer, c.email, c.phone_number, " +
                " ct.name, dt.name, wd.name " +
                " FROM orders o " +
                " JOIN customers c ON o.customer_id = c.id " +
                " JOIN cities ct ON ct.id = c.city_id " +
                " JOIN districts dt ON dt.id = c.district_id " +
                " JOIN wards wd ON wd.id = c.ward_id " +
                " WHERE o.id = :orderId";
        Mono<OrderDetailDTO> orderDetailDTOMono = databaseClient.sql(sqlStr)
                .bind("orderId", orderId)
                .map((row, rowMetadata) -> OrderDetailDTO.builder()
                        .id(row.get(0, Long.class))
                        .status(row.get(1, String.class))
                        .totalFee(row.get(2, BigDecimal.class))
                        .shippingFee(row.get(3, BigDecimal.class))
                        .createdAt(row.get(4, LocalDateTime.class))
                        .updatedAt(row.get(5, LocalDateTime.class))
                        .customer(CustomerDTO.builder()
                                .id(row.get(6, Long.class))
                                .username(row.get(7, String.class))
                                .email(row.get(8, String.class))
                                .phoneNumber(row.get(9, String.class))
                                .address(row.get(12, String.class) + ", " + row.get(11, String.class) + ", " + row.get(10, String.class))
                                .build())
                        .build()
                ).one();

        String orderItemSql = " SELECT p.id, p.name, p.original_price, oi.quantity, a.path " +
                " FROM order_items oi " +
                " JOIN products p ON p.id = oi.product_id " +
                " JOIN assets a ON a.product_id = p.id " +
                " WHERE oi.order_id = :orderId ";

        Flux<OrderItemDTO> itemFlux = databaseClient.sql(orderItemSql)
                .bind("orderId", orderId)
                .map((row, meta) -> OrderItemDTO.builder()
                        .productId(row.get(0, Long.class))
                        .productName(row.get(1, String.class))
                        .price(row.get(2, BigDecimal.class))
                        .quantity(row.get(3, Long.class))
                        .path(row.get(4, String.class))
                        .build()
                ).all();
        return orderDetailDTOMono.zipWith(itemFlux.collectList(), (order, items) -> {
            order.setOrderItemList(items);
            return order;
        });
    }
}
