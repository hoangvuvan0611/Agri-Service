package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.DistrictManagementDTO;
import com.hvv.agriservice.dto.request.CreateDistrictRequest;
import com.hvv.agriservice.entity.District;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DistrictService {
    Mono<District> createDistrict(CreateDistrictRequest request);
    Flux<DistrictManagementDTO> getDistrictToShowManagement(int page, int size);
}
