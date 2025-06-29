package com.hvv.agriservice.service;

import com.hvv.agriservice.dto.model.ReportCommonDTO;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ReportService {

    /**
     *
     * @return Thong tin tong doanh thu
     */
    Mono<BigDecimal> getTotalRevenue();

    Mono<ReportCommonDTO> getReportCommon(String unit);
}
