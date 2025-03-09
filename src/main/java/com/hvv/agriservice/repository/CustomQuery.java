package com.hvv.agriservice.repository;

public interface CustomQuery {
    public static String getRecommendationProductsByProductId =
            "SELECT p.id, p.name, p.slug, d.uses, p.original_price, p.sale_price, p.sold, p.quantity, p.featured   " +
            "  FROM products p  " +
            "  JOIN descriptions d ON p.id = d.product_id " +
            "  WHERE p.id IN (:ids);";
}
