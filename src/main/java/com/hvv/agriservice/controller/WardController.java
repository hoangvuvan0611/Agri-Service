package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.dto.request.CreateWardRequest;
import com.hvv.agriservice.service.WardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.*;
import static com.hvv.agriservice.constant.Const.Common.GET_MANAGEMENT;
import static com.hvv.agriservice.constant.Const.WardPath.BY_DISTRICT_ID;
import static com.hvv.agriservice.constant.Const.WardPath.WARD_PATH;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = WARD_PATH)
@RequiredArgsConstructor
public class WardController {

    private final WardService wardService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping(path = CREATE)
    public Mono<ResponseData<?>> create(@RequestBody CreateWardRequest request) {
        return wardService.createWard(request)
                .map(ward -> ResponseData.success("", ward));
    }

    @PutMapping(path = UPDATE)
    public ResponseEntity<?> update() {
        return null;
    }

    @GetMapping(path = GET_MANAGEMENT)
    public Mono<ResponseData<?>> getCityToShowManagement(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "20") int size
    ) {
        return wardService.getWardToShowManagement(page, size)
                .collectList()
                .map(wards -> ResponseData.success("", wards));
    }

    @GetMapping(path = BY_DISTRICT_ID)
    public Mono<ResponseData<?>> getDistrictsByDistrictId(@PathVariable String districtId) {
        return wardService.getWardsByDistrictId(districtId)
                .collectList()
                .map(districtToSelectDTOS -> ResponseData.success("", districtToSelectDTOS));
    }
}
