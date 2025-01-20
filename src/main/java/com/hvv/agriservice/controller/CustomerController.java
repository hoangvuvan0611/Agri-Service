package com.hvv.agriservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import static com.hvv.agriservice.constant.consts.Common.*;

@Slf4j
@RestController
@RequestMapping(path = CustomerPath.CUSTOMER_PATH)
public class CustomerController {

    @GetMapping(path = "/{id}")
    public RequestEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public RequestEntity<?> getAll() {
        return null;
    }

    @PostMapping(path = CREATE)
    public RequestEntity<?> create() {
        return null;
    }

    @PutMapping(path = UPDATE)
    public RequestEntity<?> update() {
        return null;
    }
}
