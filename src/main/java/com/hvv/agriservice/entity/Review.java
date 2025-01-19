package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Bảng đánh giá sản phẩm của người dùng
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @Column("id")
    private UUID id;
    @Column("product_id")
    private UUID productId;                         // Id của sản phẩm được đánh giá
    @Column("customer_id")
    private UUID customerId;                        // Id của khách hàng đánh giá
    @Column("rating")
    private Integer rating;                         // Điểm đánh giá 1 - 5
    @Column("title")
    private String title;                           // Tiêu đề đánh giá
    @Column("content")
    private String content;                         // Nội dung đánh giá
    @Column("is_approved")
    private Boolean isApproved;                     // Trạng thái duyệt đánh giá
    @Column("created_at")
    private LocalDateTime createdAt;                // Thời gian tạo đánh giá
    @Column("updated_at")
    private LocalDateTime updatedAt;                // Thời gian cập nhật đánh giá
}
