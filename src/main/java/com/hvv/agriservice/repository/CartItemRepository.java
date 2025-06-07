package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.CartItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends ReactiveCrudRepository<CartItem, Long> {
}
