package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.WardManagementDTO;
import com.hvv.agriservice.dto.model.WardToSelectDTO;
import com.hvv.agriservice.entity.Ward;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.hvv.agriservice.repository.CustomQuery.getWardByDistrictIdToSelect;
import static com.hvv.agriservice.repository.CustomQuery.getWardToShowManagement;

@Repository
public interface WardRepository extends ReactiveCrudRepository<Ward, Long> {

    @Query(getWardToShowManagement)
    Flux<WardManagementDTO> getWardToShowManagement(int offset, int size);
    @Query(getWardByDistrictIdToSelect)
    Flux<WardToSelectDTO> getWardsByDistrictId(Long districtId);
}
