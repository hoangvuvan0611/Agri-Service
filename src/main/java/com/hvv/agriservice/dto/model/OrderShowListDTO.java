package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderShowListDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String customerName;                        // Ten Khach hang
    private OrderStatusEnum status;                 // Trang thai cua don hang
    private BigDecimal shippingFee;                 // Phí giao hàng
    private BigDecimal totalFee;                    // Tổng tiền thanh toán
    private LocalDateTime createdAt;                // Thoi gian tao don hang
    private LocalDateTime updatedAt;
}
