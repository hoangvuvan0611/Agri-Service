package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.entity.User;
import com.hvv.agriservice.repository.UserRepository;
import com.hvv.agriservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Flux<User> getAll() {
        return userRepository.findAll();
    }
}
