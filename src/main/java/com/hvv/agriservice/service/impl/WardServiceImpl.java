package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.config.common.SnowflakeIdGenerator;
import com.hvv.agriservice.dto.model.WardManagementDTO;
import com.hvv.agriservice.dto.model.WardToSelectDTO;
import com.hvv.agriservice.dto.request.CreateWardRequest;
import com.hvv.agriservice.entity.Ward;
import com.hvv.agriservice.repository.WardRepository;
import com.hvv.agriservice.service.WardService;
import com.hvv.agriservice.utils.DataUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    private final SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public Mono<Ward> createWard(CreateWardRequest request) {
        Ward ward = Ward.builder()
                .id(snowflakeIdGenerator.generateId())
                .name(request.getName())
                .districtId(DataUtils.safeToLong(request.getDistrictId()))
                .postalCode(request.getPostalCode())
                .createdAt(LocalDateTime.now())
                .isNew(true)
                .build();
        return wardRepository.save(ward);
    }

    @Override
    public Flux<WardManagementDTO> getWardToShowManagement(int page, int size) {
        return wardRepository.getWardToShowManagement(size*page, size);
    }

    @Override
    public Flux<WardToSelectDTO> getWardsByDistrictId(String districtId) {
        return wardRepository.getWardsByDistrictId(DataUtils.safeToLong(districtId));
    }
}
