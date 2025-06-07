package com.hvv.agriservice.controller;

import com.hvv.agriservice.constant.Const;
import com.hvv.agriservice.dto.base.ResponseData;
import com.hvv.agriservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.hvv.agriservice.constant.Const.ReportPath.TOTAL_REVENUE;

@RestController
@RequestMapping(path = Const.ReportPath.REPORT_PATH)
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping(path = TOTAL_REVENUE)
    public Mono<ResponseData<?>> getTotalRevenue() {
        return reportService.getTotalRevenue()
                .map(total -> ResponseData.success("", total));
    }
}
