package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.config.common.SnowflakeIdGenerator;
import com.hvv.agriservice.dto.request.CreateCityRequest;
import com.hvv.agriservice.entity.City;
import com.hvv.agriservice.repository.CityRepository;
import com.hvv.agriservice.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final SnowflakeIdGenerator snowflakeIdGenerator;

    @Override
    public Mono<City> createCity(CreateCityRequest request) {
        City city = City.builder()
                .id(snowflakeIdGenerator.generateId())
                .name(request.getName())
                .postalCode(request.getPostalCode())
                .createdAt(LocalDateTime.now())
                .isNew(true)
                .build();
        return cityRepository.save(city);
    }

    @Override
    public Flux<City> getCityToShowManagement(int page, int size) {
        return cityRepository.getProductToShowManagement(page*size, size);
    }
}
