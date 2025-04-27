package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.CityManagementDTO;
import com.hvv.agriservice.entity.City;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.hvv.agriservice.repository.CustomQuery.getCityToShowManagement;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Long> {
    @Query(getCityToShowManagement)
    Flux<CityManagementDTO> getProductToShowManagement(int offset, int size);
}
