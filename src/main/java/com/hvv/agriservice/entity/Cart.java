package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Đối tượng giỏ hàng
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    @Column("id")
    private UUID id;
    @Column("customer_id")
    private UUID customerId;                    // Id của khách hàng
    @Column("created_at")
    private LocalDateTime createdAt;            // Thời gian tạo giỏ hàng
    @Column("updated_at")
    private LocalDateTime updatedAt;            // Thời gian cập nhật giỏ hàng
}
