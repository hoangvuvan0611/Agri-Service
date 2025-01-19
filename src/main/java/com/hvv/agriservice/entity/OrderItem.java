package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Thông tin chi tiết sản phẩm trong đơn hàng:
 * Bảng này sẽ lưu thông tin sản phẩm lúc đặt hàng như tên, số lượng, giá mà không truy vấn từ bảng product
 * vì lúc các bản ghi của bảng product cập nhật sẽ gây sai lệch thông tin
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @Column("id")
    private UUID id;
    @Column("order_id")
    private UUID orderId;                       // Id của đơn hàng
    @Column("product_id")
    private UUID productId;                     // Id product
    @Column("name")
    private String name;                        // Tên sản phẩm đặt hàng
    @Column("quantity")
    private Long quantity;                      // Số lượng sản phẩm đặt hàng
    @Column("price")
    private BigDecimal price;                   // Giá sản phẩm khi đặt hàng
    @Column("created_at")
    private LocalDateTime createdAt;            // Thời điểm tạo đơn hàng
    @Column("updated_at")
    private LocalDateTime updatedAt;            // Thời điểm cập nhật đơn hàng
}
