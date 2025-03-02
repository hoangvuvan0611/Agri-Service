package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Doi tuong don hang
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("customer_id")
    private Long customerId;                        // Id cua khach hang
    @Column("status")
    private OrderStatusEnum status;                 // Trang thai cua don hang
    @Column("shipping_fee")
    private BigDecimal shippingFee;                 // Phí giao hàng
    @Column("delivery_info_id")
    private Long deliveryInfoId;                    // Thông tin địa chỉ giao hàng
    @Column("total_fee")
    private BigDecimal totalFee;                    // Tổng tiền thanh toán
    @Column("payment_id")
    private Long paymentId;                         // Tham chiếu đến thông tin thanh toán
    @Column("coupon_id")
    private Long couponId;                          // Mã giảm giá nếu có
    @Column("affiliate_id")
    private Long affiliateId;                       // Mã liên kết (mã giới thiệu)
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
    @Transient
    private boolean isNew;
}
