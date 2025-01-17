package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * Đối tượng chứa thông tin thuộc tính khác: màu sắc, kich thước
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attributes")
public class Attributes {
    @Id
    @Column("id")
    private UUID id;
    @Column("name")
    private String name;                    // Tên thuộc tính
    @Column("description")
    private String description;             // Mô tả
}
