package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.ProductAsset;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAssetRepository extends ReactiveCrudRepository<ProductAsset, Long> {
}
