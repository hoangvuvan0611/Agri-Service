package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.District;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends ReactiveCrudRepository<District, Long> {
}
