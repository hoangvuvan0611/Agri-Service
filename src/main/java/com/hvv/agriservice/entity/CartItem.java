package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Lưu thông tin chi tiết về các sản phẩm trong giỏ hàng
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem {
    @Id
    @Column("id")
    private UUID id;
    @Column("cart_id")
    private UUID cartId;                    // Id giỏ hàng
    @Column("product_id")
    private UUID productId;                 // id của sản phẩm
    @Column("quantity")
    private Long quantity;                  // Số lượng sản phẩm
    @Column("price")
    private BigDecimal price;               // Giá của sản phẩm tại thời điểm thêm vào giỏ hàng
    @Column("created_at")
    private LocalDateTime createdAt;        // Thời gian thêm sản phẩm vào giỏ hàng
    @Column("updated_at")
    private LocalDateTime updatedAt;        // Thời gian cập nhật số lượng sản phẩm trong giỏ hàng
}
