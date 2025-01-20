package com.hvv.agriservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hvv.agriservice.constant.consts.Common.*;

@Slf4j
@RestController
@RequestMapping(path = ReviewPath.REVIEW_PATH)
public class ReviewController {

    @RequestMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return null;
    }

    @RequestMapping(path = ALL)
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
