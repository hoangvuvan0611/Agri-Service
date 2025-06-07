package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.ProductDTO;
import com.hvv.agriservice.dto.model.ProductManagementDTO;
import com.hvv.agriservice.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.hvv.agriservice.repository.CustomQuery.*;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByNameContainingIgnoreCase(String name);
    Mono<Product> findByNameIgnoreCase(String name);

    @Query(getRecommendationProductsByProductId)
    Flux<ProductDTO> getRecommendationsProductById(List<Long> ids);

    Flux<Product> findAllBy(Pageable pageable);

    Mono<Long> count();

    @Query("    SELECT p.id, p.name, p.slug, d.uses, p.original_price, p.sale_price, p.sold, p.quantity, p.featured, a.path " +
            "   FROM products p  " +
            "   JOIN descriptions d ON p.id = d.product_id " +
            "   JOIN assets a ON p.id = a.product_id " +
            "   LIMIT :size " +
            "   OFFSET :offset ;")
    Flux<ProductDTO> getProductsToShowInit(int offset, int size);

    @Query("    SELECT p.id, p.name, p.slug, d.uses, p.original_price, " +
            "   p.sale_price, p.sold, p.quantity, p.featured, a.path " +
            "   FROM products p  " +
            "   JOIN descriptions d ON p.id = d.product_id " +
            "   JOIN assets a ON p.id = a.product_id " +
            "   WHERE p.slug = :slug")
    Mono<ProductDTO> getProductBySlug(String slug);

    @Query("SELECT COUNT(*) FROM products")
    Mono<Long> getTotal();

    @Query("    SELECT p.id, p.name, p.original_price, p.sale_price, p.sold, a.path, " +
            "   p.quantity, c.name as category, p.status" +
            "   FROM products p " +
            "   JOIN categories c ON c.id = p.category_id " +
            "   JOIN assets a ON p.id = a.product_id "   +
            "   LIMIT :size " +
            "   OFFSET :offset ;")
    Flux<ProductManagementDTO> getProductToShowManagement(int offset, int size);

    @Query("    SELECT p.id, p.name, p.slug , a.path as path " +
            "   FROM products p " +
            "   JOIN assets a ON p.id = a.product_id " +
            "   WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "   LIMIT 5 ")
    Flux<ProductDTO> searchProductByName(String keyword);

    /**
     * Lay danh sach san pham ban chay nhat (dua theo so luong) trong thang
     * @return
     */
    @Query("SELECT p.id, p.name, p.original_price, a.path as path, sum(oi.quantity) AS quantity " +
            "FROM products p " +
            "JOIN order_items oi ON p.id = oi.product_id " +
            "JOIN assets a ON p.id = a.product_id " +
            "WHERE oi.created_at >= CURRENT_DATE - INTERVAL '30 days' " +
            "GROUP BY p.id, p.name, path " +
            "ORDER BY quantity DESC LIMIT :size;")
    Flux<ProductDTO> getListProductBestSellerInMonth(int size);
}
