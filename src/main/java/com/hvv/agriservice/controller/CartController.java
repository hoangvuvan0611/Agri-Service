package com.hvv.agriservice.controller;

import static com.hvv.agriservice.constant.consts.Common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = CartPath.CART_PATH)
public class CartController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @GetMapping(path = ALL)
    public ResponseEntity<?> getAll() {
        return null;
    }

    @PostMapping(path = CREATE)
    public ResponseEntity<?> create() {
        return null;
    }

    @PutMapping(path = UPDATE)
    public ResponseEntity<?> update() {
        return null;
    }
}
