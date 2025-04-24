package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.DistrictManagementDTO;
import com.hvv.agriservice.entity.District;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.hvv.agriservice.repository.CustomQuery.getDistrictToShowManagement;

@Repository
public interface DistrictRepository extends ReactiveCrudRepository<District, Long> {
    @Query(getDistrictToShowManagement)
    Flux<DistrictManagementDTO> getDistrictToShowManagement(int offset, int size);
}
