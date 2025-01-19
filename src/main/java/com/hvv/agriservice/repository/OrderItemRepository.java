package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.OrderItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends ReactiveCrudRepository<OrderItem, UUID> {
}
