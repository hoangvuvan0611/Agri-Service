package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_asset")
public class ProductAsset implements Persistable<Long> {
    @Id
    @Column("id")
    private Long id;
    @Column("product_id")
    private Long productId;                     // Id của sản phẩm
    @Column("asset_id")
    private Long assetId;                       // Id của asset
    @Column("type")
    private String type;
    @Transient
    private boolean isNew;
}
