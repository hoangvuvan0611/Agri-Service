package com.hvv.agriservice.repository.impl;

import com.hvv.agriservice.constant.Const;
import com.hvv.agriservice.dto.model.ReportCommonDTO;
import com.hvv.agriservice.repository.CustomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {

    private final DatabaseClient databaseClient;
    private final Logger logger = LoggerFactory.getLogger(CustomRepositoryImpl.class);

    @Override
    public Mono<ReportCommonDTO> getReportCommonByUnit(String unit) {
        if (!StringUtils.hasText(unit)) {
            unit = Const.ReportUnit.UNIT_MONTH;
        }

        String query = "SELECT " +
                " ( SELECT COUNT(*) AS total_orders FROM orders WHERE created_at >= CURRENT_DATE - INTERVAL '30 days' ) AS totalRevenue" +
                " ";
        return null;
    }
}
