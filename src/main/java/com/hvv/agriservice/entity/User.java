package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.RoleEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Đối tượng người dùng tại trang quản trị
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column("id")
    private UUID id;
    @Column("username")
    private String username;                            // Tên người dùng
    @Column("full_name")
    private String fullName;                            // Họ tên đầy đủ
    @Column("date_of_birth")
    private LocalDate dateOfBirth;                      // Ngày sinh
    @Column("created_at")
    private LocalDateTime createdAt;                    // Thời gian tạo user
    @Column("created_by")
    private String createdBy;                             // Người tạo user
    @Column("updated_at")
    private LocalDateTime updatedAt;                    // Thời gian cập nhật user
    @Column("updated_by")
    private String updatedBy;                             // Người cập nhật user
}
