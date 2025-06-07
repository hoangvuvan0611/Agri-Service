package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {
}
