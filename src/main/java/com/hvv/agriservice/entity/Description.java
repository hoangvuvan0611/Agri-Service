package com.hvv.agriservice.entity;

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
@Table(name = "descriptions")
public class Description implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("product_id")
    private Long productId;
    @Column("certificate")
    private String certificate;
    @Column("origin")
    private String origin;
    @Column("uses")
    private String uses;
    @Column("instructions_for_use")
    private String instructionsForUse;
    @Column("preserving_instruction")
    private String preservingInstruction;
    @Column("expiry")
    private String expiry;
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("created_by")
    private String createdBy;
    @Column("updated_at")
    private LocalDateTime updatedAt;
    @Column("updated_by")
    private String updatedBy;
    @Transient
    private boolean isNew;
}
