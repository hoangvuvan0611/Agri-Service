package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.CityManagementDTO;
import com.hvv.agriservice.dto.model.CityToSelectDTO;
import com.hvv.agriservice.dto.request.CreateCityRequest;
import com.hvv.agriservice.entity.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {
    Mono<City> createCity(CreateCityRequest request);

    Flux<CityManagementDTO> getCityToShowManagement(int page, int size);
    Flux<CityToSelectDTO> getCitiesToSelect();
}
