package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Đối tượng chứa thông tin thuộc tính khác: màu sắc, kich thước
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attributes")
public class Attributes implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;                    // Tên thuộc tính
    @Column("description")
    private String description;             // Mô tả
    @Transient
    private boolean isNew;
}
