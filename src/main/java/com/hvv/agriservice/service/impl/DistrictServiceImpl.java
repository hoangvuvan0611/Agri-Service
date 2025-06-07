package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.config.common.SnowflakeIdGenerator;
import com.hvv.agriservice.dto.model.DistrictManagementDTO;
import com.hvv.agriservice.dto.model.DistrictToSelectDTO;
import com.hvv.agriservice.dto.request.CreateDistrictRequest;
import com.hvv.agriservice.entity.District;
import com.hvv.agriservice.repository.DistrictRepository;
import com.hvv.agriservice.service.DistrictService;
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
public class DistrictServiceImpl implements DistrictService {

    private final SnowflakeIdGenerator snowflakeIdGenerator;
    private final DistrictRepository districtRepository;
    @Override
    public Mono<District> createDistrict(CreateDistrictRequest request) {
        District district = District.builder()
                .id(snowflakeIdGenerator.generateId())
                .name(request.getName())
                .cityId(request.getCityId())
                .createdAt(LocalDateTime.now())
                .isNew(true)
                .createdBy("SYSTEM")
                .build();
        return districtRepository.save(district);
    }

    @Override
    public Flux<DistrictManagementDTO> getDistrictToShowManagement(int page, int size) {
        return districtRepository.getDistrictToShowManagement(page*size, size);
    }

    @Override
    public Flux<DistrictToSelectDTO> getDistrictsByCityId(String cityId) {
        return districtRepository.getDistrictsByCityId(DataUtils.safeToLong(cityId));
    }
}
