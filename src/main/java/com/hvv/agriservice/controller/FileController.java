package com.hvv.agriservice.controller;

import com.hvv.agriservice.constant.Const;
import com.hvv.agriservice.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Const.FilePath.FILE_PATH)
@RequiredArgsConstructor
public class FileController {

    private final MinioService minioService;

    @GetMapping("/{fileName}")
    public Mono<String> getImageUrl(@PathVariable String fileName) {
        return minioService.getDirectObjectUrl(fileName);
    }
}
