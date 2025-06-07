package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Đối tượng thể hiện tiếp thị liên kết
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "affiliates")
public class Affiliate implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("customer_id")
    private Long customerId;                    // Id của khách hàng liên kết
    @Column("code")
    private String code;                        // Mã code của affiliate
    @Column("commission")
    private BigDecimal commission;              // Tỉ lệ hoa hồng nhận được khi có đơn hàng
    @Column("balance")
    private BigDecimal balance;                 // Số dư hoa hồng hiện tại
    @Column("is_active")
    private Boolean isActive;                   // Trạng thái kích hoạt của affiliate
    @Column("created_at")
    private LocalDateTime createdAt;            // Thời gian tạo
    @Column("updated_at")
    private LocalDateTime updateAt;             // Thời gian cập nhật
    @Transient
    private boolean isNew;
}
