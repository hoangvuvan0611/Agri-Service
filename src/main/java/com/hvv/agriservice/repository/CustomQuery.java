package com.hvv.agriservice.repository;

public interface CustomQuery {
    String getRecommendationProductsByProductId =
            "SELECT p.id, p.name, p.slug, d.uses, p.original_price, p.sale_price, p.sold, p.quantity, p.featured, a.path " +
            "  FROM products p  " +
            "  JOIN descriptions d ON p.id = d.product_id " +
            "  JOIN assets a ON p.id = a.product_id " +
            "  WHERE p.id IN (:ids);";

    String getProductsToShowInit =
            "SELECT p.id, p.name, p.slug, d.uses, p.original_price, p.sale_price, p.sold, p.quantity, p.featured, a.path " +
            "  FROM products p  " +
            "  JOIN descriptions d ON p.id = d.product_id " +
            "  JOIN assets a ON p.id = a.product_id " +
            "  LIMIT :size " +
            "  OFFSET :offset ;";
}


