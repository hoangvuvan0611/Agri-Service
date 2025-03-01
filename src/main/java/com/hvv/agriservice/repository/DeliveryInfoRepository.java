package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.DeliveryInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryInfoRepository extends ReactiveCrudRepository<DeliveryInfo, Long> {
}
