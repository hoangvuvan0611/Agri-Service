package com.hvv.agriservice.service;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ReportService {

    /**
     *
     * @return Thong tin tong doanh thu
     */
    Mono<BigDecimal> getTotalRevenue();


}
