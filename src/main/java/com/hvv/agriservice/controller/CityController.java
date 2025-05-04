package com.hvv.agriservice.controller;

import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.dto.request.CreateCityRequest;
import com.hvv.agriservice.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.*;
import static com.hvv.agriservice.constant.Const.Common.GET_MANAGEMENT;
import static com.hvv.agriservice.constant.Const.Common.GET_SELECT;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = CityPath.CITY_PATH)
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping(path = CREATE)
    public Mono<ResponseData<?>> create(@RequestBody CreateCityRequest request) {
        return cityService.createCity(request)
                .map(city -> ResponseData.success("", city));
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
        return cityService.getCityToShowManagement(page, size)
                .collectList()
                .map(cities -> ResponseData.success("", cities));
    }

    @GetMapping(path = GET_SELECT)
    public Mono<ResponseData<?>> getCitiesToSelect () {
        return cityService.getCitiesToSelect()
                .collectList()
                .map(cities -> ResponseData.success("", cities));
    }
}
