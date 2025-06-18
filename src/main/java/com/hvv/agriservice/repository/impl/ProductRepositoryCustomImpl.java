package com.hvv.agriservice.repository.impl;

import com.hvv.agriservice.dto.model.ProductManagementDTO;
import com.hvv.agriservice.repository.ProductRepositoryCustom;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final DatabaseClient databaseClient;

    @Override
    public Flux<ProductManagementDTO> findProductsByMultiCondition(int size, int offset, String keyword, Long categoryId) {
        StringBuilder stringBuilder = new StringBuilder("SELECT p.id, p.name, p.original_price, p.sale_price, p.sold, a.path, p.quantity, c.name as category, p.status " +
                " FROM products p " +
                " JOIN categories c ON c.id = p.category_id " +
                " JOIN assets a ON p.id = a.product_id " +
                " WHERE 1=1 ");
        if (StringUtils.hasText(keyword)) {
            stringBuilder.append(" AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) ");
        }
        if (categoryId != null) {
            stringBuilder.append(" AND p.category_id = :categoryId ");
        }
        stringBuilder.append(" LIMIT :size OFFSET :offset ;");

        DatabaseClient.GenericExecuteSpec spec = databaseClient.sql(stringBuilder.toString());
        if (StringUtils.hasText(keyword)) {
            spec = spec.bind("keyword", keyword);
        }
        if (categoryId != null) {
            spec = spec.bind("categoryId", categoryId);
        }
        return spec.map((row, rowMetadata) -> ProductManagementDTO.builder()
                        .id(row.get(0, Long.class))
                        .name(row.get(1, String.class))
                        .originalPrice(row.get(2, BigDecimal.class))
                        .salePrice(row.get(3, BigDecimal.class))
                        .sold(row.get(4, Long.class))
                        .path(row.get(5, String.class))
                        .quantity(row.get(6, Long.class))
                        .category(row.get(7, String.class))
                        .status(row.get(8, String.class))
                        .build()
                ).all();
    }
}
