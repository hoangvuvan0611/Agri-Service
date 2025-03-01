package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.City;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Long> {
}
