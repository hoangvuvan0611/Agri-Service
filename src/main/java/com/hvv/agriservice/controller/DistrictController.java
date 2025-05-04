package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.dto.request.CreateDistrictRequest;
import com.hvv.agriservice.service.DistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.*;
import static com.hvv.agriservice.constant.Const.Common.GET_MANAGEMENT;
import static com.hvv.agriservice.constant.Const.DistrictPath.BY_CITY_ID;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = DistrictPath.DISTRICT_PATH)
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping(path = CREATE)
    public Mono<ResponseData<?>> create(@RequestBody CreateDistrictRequest request) {
        return districtService.createDistrict(request)
                .map(district -> ResponseData.success("", district));
    }

    @PutMapping(path = UPDATE)
    public ResponseEntity<?> update() {
        return null;
    }

    @GetMapping(path = GET_MANAGEMENT)
    public Mono<ResponseData<?>> getDistrictToShowManagement(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return districtService.getDistrictToShowManagement(page, size)
                .collectList()
                .map(districts -> ResponseData.success("", districts));
    }

    @GetMapping(path = BY_CITY_ID)
    public Mono<ResponseData<?>> getDistrictsByCityId(@PathVariable String cityId) {
        return districtService.getDistrictsByCityId(cityId)
                .collectList()
                .map(districtToSelectDTOS -> ResponseData.success("", districtToSelectDTOS));
    }
}
