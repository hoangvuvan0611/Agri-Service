package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Attributes;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends ReactiveCrudRepository<Attributes, Long> {
}
