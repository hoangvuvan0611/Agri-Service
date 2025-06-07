package com.hvv.agriservice.service.impl;

import com.hvv.agriservice.repository.OrderRepository;
import com.hvv.agriservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrderRepository orderRepository;

    @Override
    public Mono<BigDecimal> getTotalRevenue() {
        return orderRepository.getTotalRevenue();
    }
}
