package com.hvv.agriservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hvv.agriservice.constant.Const.*;

@RestController
@RequestMapping(path = CartItemPath.CART_ITEM_PATH)
public class CartItemController {

    @GetMapping(path = "/{id}")
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
