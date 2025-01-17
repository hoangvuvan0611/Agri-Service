package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_asset")
public class ProductAsset {
    @Id
    @Column("id")
    private UUID id;
    @Column("product_id")
    private UUID productId;                     // Id của sản phẩm
    @Column("asset_id")
    private UUID assetId;                       // Id của asset
    @Column("type")
    private String type;
}
