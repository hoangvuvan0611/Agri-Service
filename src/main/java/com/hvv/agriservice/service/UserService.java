package com.hvv.agriservice.service;

import com.hvv.agriservice.entity.User;
import reactor.core.publisher.Flux;

public interface UserService {
    Flux<User> getAll();
}
