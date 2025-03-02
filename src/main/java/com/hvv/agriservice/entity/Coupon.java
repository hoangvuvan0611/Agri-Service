package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.CouponTypeEnum;
import com.hvv.agriservice.constant.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Đối tượng mã giảm giá
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coupons")
public class Coupon implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("coupon_code")
    private String couponCode;                  // Mã code giảm giá
    @Column("coupon_type")
    private CouponTypeEnum couponType;          // Loại hình phiếu giảm giá
    @Column("coupon_value")
    private BigDecimal couponValue;             // Giá trị phiếu giảm giá, dựa vào type để xem % hay tiền
    @Column("coupon_start_time")
    private LocalDateTime couponStartTime;      // Thời gian bắt đầu áp dụng phiếu giảm giá
    @Column("coupon_end_time")
    private LocalDateTime couponEndTime;        // Thời gian kết thúc áp dụng phiếu giảm giá
    @Column("coupon_min_spend")
    private BigDecimal couponMinSpend;          // Số tiền tối thiểu để áp dụng phiếu giảm giá
    @Column("coupon_max_spend")
    private BigDecimal couponMaxSpend;          // Số tiền giảm tối đa khi được áp dụng phiếu giảm giá
    @Column("coupon_uses_per_customer")
    private Long couponUsesPerCustomer;         // Số lần sử dụng coupon tối đa cho mỗi khách hàng
    @Column("coupon_uses_per_coupon")
    private Long couponUsesPerCoupon;           // Số lần sử dụng coupon tối đa cho chính coupon đó
    @Column("coupon_status")
    private StatusEnum coupon_status;           // Trạng thái của coupon, có thể là đang hoạt động, hết hạn hoặc bị vô hiệu hóa
    @Column("created_at")
    private LocalDateTime createdAt;            // Thời gian tạo mới phiếu giảm giá
    @Column("created_by")
    private String createdBy;                     // User tạo mới phiếu giảm giá
    @Column("updated_at")
    private LocalDateTime updatedAt;            // Thời gian cập nhật thông tin phiếu giảm giá
    @Column("updated_by")
    private String updatedBy;                     // User cập nhật phiếu giảm giá
    @Transient
    private boolean isNew;
}
