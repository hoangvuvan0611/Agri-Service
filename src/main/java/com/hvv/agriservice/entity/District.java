package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Doi tuong chua danh sach quan huyen
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "districts")
public class District implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;                        // Ten quan huyen
    @Column("postal_code")
    private String postalCode;                  // Ma buu dien cua quan huyen
    @Column("city_id")
    private Long cityId;
    @Column("created_at")
    private LocalDateTime createdAt;            // Thoi gian tao moi
    @Column("created_by")
    private String createdBy;                     // Nguoi tao thong tin
    @Column("updated_at")
    private LocalDateTime updatedAt;            // Thoi gian cap nhat
    @Column("updated_by")
    private String updatedBy;                     // Nguoi cap nhat thong tin
    @Transient
    private boolean isNew;
}
