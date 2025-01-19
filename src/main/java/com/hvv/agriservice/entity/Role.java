package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.RoleEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Đối tượng vai trò cho người dùng trong trang quản trị
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @Column("id")
    private UUID id;
    @Column("name")
    private RoleEnum name;                                  // Tên của vai trò
    @Column("description")
    private String description;                             // Mô tả cho vai trò
    @Column("created_at")
    private LocalDateTime createdAt;                        // Thời gian tạo mới vai trò
    @Column("created_by")
    private String createdBy;                                 // Người tạo mới vai trò
    @Column("updated_at")
    private LocalDateTime updatedAt;                        // Thời gian cập nhật thông tin vai trò
    @Column("updated_by")
    private String updatedBy;                                 // Người cập nhật vai trò
}
