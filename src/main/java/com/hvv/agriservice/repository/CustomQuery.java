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
            "SELECT p.id, p.name, p.original_price, p.sale_price, p.sold, a.path, " +
            "p.quantity, c.name as category, p.status" +
            "  FROM products p " +
            "  JOIN categories c ON c.id = p.category_id " +
            "  JOIN assets a ON p.id = a.product_id "   +
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

    String getWardToShowManagement =
            "SELECT w.id, w.name, w.postal_code, w.created_at, w.updated_at, d.name as district, c.name as city" +
            " FROM wards w " +
            " JOIN districts d on d.id = w.district_id " +
            " JOIN cities c on c.id = d.city_id" +
            " LIMIT :size " +
            " OFFSET :offset ;";

    String getWardByDistrictIdToSelect =
            "SELECT w.id, w.name " +
            " FROM wards w " +
            " JOIN districts d on d.id = w.district_id " +
            " WHERE w.district_id = :districtId";

    String getCitiesToSelect =
            "SELECT c.id, c.name " +
            "FROM cities c ";

    String searchByName =
            "SELECT p.id, p.name, p.slug , a.path as path" +
            " FROM products p " +
            " JOIN assets a ON p.id = a.product_id " +
            " WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            " LIMIT 5";
}


