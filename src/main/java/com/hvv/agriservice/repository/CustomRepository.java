package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.ReportCommonDTO;
import reactor.core.publisher.Mono;

public interface CustomRepository {
    Mono<ReportCommonDTO> getReportCommonByUnit(String unit);
}
