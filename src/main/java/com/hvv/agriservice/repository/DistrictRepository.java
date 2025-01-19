package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.District;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DistrictRepository extends ReactiveCrudRepository<District, UUID> {
}
