package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Doi tuong chua thong tin phuong xa
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wards")
public class Ward {
    @Id
    @Column("id")
    private UUID id;
    @Column("name")
    private String name;                        // Ten phuong, xa
    @Column("postal_code")
    private String postalCode;                  // Ma buu dien cua quan huyen
    @Column("created_at")
    private LocalDateTime createdAt;            // Thoi gian tao moi
    @Column("created_by")
    private String createdBy;                     // Nguoi tao thong tin
    @Column("updated_at")
    private LocalDateTime updatedAt;            // Thoi gian cap nhat
    @Column("updated_by")
    private String updatedBy;                     // Nguoi cap nhat thong tin
}
