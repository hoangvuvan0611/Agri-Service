package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, UUID> {
}
