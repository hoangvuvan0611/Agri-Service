package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Đối tượng chứa quan hệ giữa user và role, một user có thể có nhiều role
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users_roles")
public class UserRole implements Persistable<Long> {
    @Id
    private Long id;
    @Column("user_id")
    private Long userId;                        // Id của đối tượng user
    @Column("role_id")
    private Long roleId;                        // Id của đối tượng role
    @Column("created_at")
    private LocalDateTime createdAt;            // Thời gian phân quyền
    @Column("created_by")
    private String createdBy;                     // User phân quyền
    @Transient
    private boolean isNew;
}
