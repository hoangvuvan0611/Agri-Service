package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.WardManagementDTO;
import com.hvv.agriservice.dto.model.WardToSelectDTO;
import com.hvv.agriservice.dto.request.CreateWardRequest;
import com.hvv.agriservice.entity.Ward;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WardService {
    Mono<Ward> createWard(CreateWardRequest request);
    Flux<WardManagementDTO> getWardToShowManagement(int page, int size);
    Flux<WardToSelectDTO> getWardsByDistrictId(String districtId);
}
