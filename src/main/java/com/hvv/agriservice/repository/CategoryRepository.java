package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, UUID> {
}
