package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.StatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category implements Persistable<Long> {
    @Id
    private Long id;
    @Column("name")
    private String name;                // Ten danh muc
    @Column("slug")
    private String slug;                // Duong dan cua danh muc
    @Column("description")
    private String description;         // Mo ta ve danh muc
    @Column("image")
    private String image;               // Duong dan anh danh muc
    @Column("status")
    private StatusEnum status;          // Trang thai cua danh muc
    @Column("created_at")
    private LocalDateTime createdAt;    // Thoi gian tao danh muc
    @Column("created_by")
    private String createdBy;             // User tao danh muc
    @Column("updated_at")
    private LocalDateTime updatedAt;    // Thoi gian cap nhat danh muc
    @Column("updated_by")
    private String updatedBy;             // User cap nhat danh muc
    @Transient
    private boolean isNew;
}
