package com.hvv.agriservice.repository;

import com.hvv.agriservice.entity.Affiliate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AffiliateRepository extends ReactiveCrudRepository<Affiliate, UUID> {
}
