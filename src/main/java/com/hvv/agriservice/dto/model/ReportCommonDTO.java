package com.hvv.agriservice.dto.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportCommonDTO {
    private BigDecimal totalRevenue;                // Tong doanh thu
    private Double changeRevenuePercent;            // Tang giam doanh thu %
    private Long orderTotal;                        // Tong so luong don hang
    private Double changeOrderPercent;
    private Long productTotal;                      // So luong san pham da ban duoc
    private Double changeProductPercent;
    private Long customerTotal;                     // So luong khach hang moi
    private Double changeCustomerPercent;
}
