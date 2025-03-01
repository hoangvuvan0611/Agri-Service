package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Doi tuong san pham, duoc ban tren trang web
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @Column("id")
    private Long id;
    @Column("category_id")
    private Long categoryId;
    @Column("name")
    private String name;                    // Ten san pham
    @Column("slug")
    private String slug;                    // Duong dan cua san pham
    @Column("description")
    private String description;             // Mo ta san pham
    @Column("original_price")
    private BigDecimal originalPrice;       // Gia ban cua san pham
    @Column("sale_price")
    private BigDecimal salePrice;           // Gia ban cua san pham sau giam gia
    @Column("discount")
    private BigDecimal discount;            // Giam gia cua san pham
    @Column("quantity")
    private Long quantity;                  // So luong san pham con lai
    @Column("sold")
    private Long sold;                      // So luong san pham da ban
    @Column("status")
    private StatusEnum status;              // Trang thai cua san pham, dang kinh doanh hoac ngung kinh doanh
    @Column("featured")
    private Boolean featured;               // Co la san pham noi bat hay khong
    @Column("expiry_period")
    private Integer expiryPeriod;           // Thoi gian het han
    @Column("unit")
    private String unit;
    @Column("created_at")
    private LocalDateTime createdAt;        // Thoi gian tao san pham
    @Column("created_by")
    private String createdBy;                 // User tao san pham
    @Column("updated_at")
    private LocalDateTime updatedAt;        // Thoi gian cap nhat san pham
    @Column("updated_by")
    private String updatedBy;                 // User cap nhat san pham
}
