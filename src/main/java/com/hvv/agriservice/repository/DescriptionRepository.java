package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Description;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DescriptionRepository extends ReactiveCrudRepository<Description, Long> {
}
