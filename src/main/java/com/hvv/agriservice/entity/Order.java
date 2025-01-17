package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Doi tuong don hang
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @Column("id")
    private UUID id;
    @Column("customer_id")
    private UUID customerId;                        // Id cua khach hang
    @Column("status")
    private OrderStatusEnum status;                 // Trang thai cua don hang
    @Column("shipping_fee")
    private BigDecimal shippingFee;                 // Phí giao hàng
    @Column("delivery_info_id")
    private UUID deliveryInfoId;                    // Thông tin địa chỉ giao hàng
    @Column("total_fee")
    private BigDecimal totalFee;                    // Tổng tiền thanh toán
    @Column("payment_id")
    private UUID paymentId;                         // Tham chiếu đến thông tin thanh toán
    @Column("coupon_id")
    private UUID couponId;                          // Mã giảm giá nếu có
    @Column("affiliate_id")
    private UUID affiliateId;                       // Mã liên kết (mã giới thiệu)
    @Column("created_at")
    private LocalDateTime createdAt;                // Thoi gian tao don hang
    @Column("canceled_at")
    private LocalDateTime canceledAt;               // Thoi diem huy don hang
    @Column("completed_at")
    private LocalDateTime completedAt;              // Thoi diem hoan thanh don hang
    @Column("delivery_at")
    private LocalDateTime deliveryAt;               // Thoi diem giao hang cho don vi van chuyen
    @Column("updated_at")
    private LocalDateTime updatedAt;                // Thoi gian cập nhật
}
