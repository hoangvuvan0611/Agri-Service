package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Assets;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends ReactiveCrudRepository<Assets, Long> {
}
