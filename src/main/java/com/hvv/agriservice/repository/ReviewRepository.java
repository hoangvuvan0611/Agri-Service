package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, UUID> {
}
