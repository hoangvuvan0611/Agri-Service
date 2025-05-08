package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long customerId;                        // Id cua khach hang
    private OrderStatusEnum status;                 // Trang thai cua don hang
    private BigDecimal shippingFee;                 // Phí giao hàng
    private Long deliveryInfoId;                    // Thông tin địa chỉ giao hàng
    private BigDecimal totalFee;                    // Tổng tiền thanh toán
    private Long paymentId;                         // Tham chiếu đến thông tin thanh toán
    private Long couponId;                          // Mã giảm giá nếu có
    private Long affiliateId;                       // Mã liên kết (mã giới thiệu)
    private LocalDateTime createdAt;                // Thoi gian tao don hang
    private LocalDateTime canceledAt;               // Thoi diem huy don hang
    private LocalDateTime completedAt;              // Thoi diem hoan thanh don hang
    private LocalDateTime deliveryAt;               // Thoi diem giao hang cho don vi van chuyen
    private LocalDateTime updatedAt;
}
