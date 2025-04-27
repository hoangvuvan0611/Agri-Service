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

    String getProductBySlug =
            "SELECT p.id, p.name, p.slug, d.uses, p.original_price, " +
            "p.sale_price, p.sold, p.quantity, p.featured, a.path " +
            "  FROM products p  " +
            "  JOIN descriptions d ON p.id = d.product_id " +
            "  JOIN assets a ON p.id = a.product_id " +
            "  WHERE p.slug = :slug";

    String getCountAllProduct =
            "SELECT COUNT(*) FROM products";

    String getProductToShowManagement =
            "SELECT p.id, p.name, p.original_price, p.sale_price, p.sold, " +
            "p.quantity, c.name as category, p.status" +
            "  FROM products p " +
            "  JOIN categories c ON c.id = p.category_id " +
            "  LIMIT :size " +
            "  OFFSET :offset ;";

    String getCityToShowManagement =
            "SELECT c.id, c.name, c.postal_code, c.created_at, c.updated_at " +
            "  FROM cities c " +
            "  LIMIT :size " +
            "  OFFSET :offset ;";

    String getDistrictToShowManagement =
            "SELECT d.id, d.name, d.postal_code, d.created_at, d.updated_at, c.name as city" +
            "  FROM districts d " +
            "  JOIN cities c on d.city_id = c.id " +
            "  LIMIT :size " +
            "  OFFSET :offset ;";
}


