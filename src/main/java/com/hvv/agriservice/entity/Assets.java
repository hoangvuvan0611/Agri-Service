package com.hvv.agriservice.entity;

import com.hvv.agriservice.constant.enums.AssetsTypeEnum;
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
@Table(name = "assets")
public class Assets implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("filename")
    private String filename;                    // Tên file
    @Column("path")
    private String path;                        // Đường dẫn tới file assets
    @Column("type")
    private AssetsTypeEnum type;
    @Column("size")
    private Long size;
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("updated_at")
    private LocalDateTime updatedAt;
    @Transient
    private boolean isNew;
}
