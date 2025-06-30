package com.hvv.agriservice.repository;

import com.hvv.agriservice.dto.model.OrderShowListDTO;
import com.hvv.agriservice.dto.model.ReportCommonDTO;
import com.hvv.agriservice.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long>, OrderCustomRepository {
    Flux<Order> findAllBy(Pageable pageable);
    Mono<Order> findById(Long id);

    @Query("SELECT COALESCE(SUM(total_fee), 0) AS total_revenue\n" +
            "FROM orders\n" +
            "WHERE status = 'COMPLETED'\n" +
            "  AND completed_at >= CURRENT_DATE - INTERVAL '30 days';")
    Mono<BigDecimal> getTotalRevenue();

//    Mono<Long> getCompareRevenue();

    /**
     *
     * @return so luong don hang trong 30 ngay
     */
    @Query("SELECT COUNT(*) AS total_orders\n" +
            "FROM orders\n" +
            "WHERE created_at >= CURRENT_DATE - INTERVAL '30 days';")
    Mono<Long> countTotalOrderIn30day();

    @Query(" SELECT o.id, c.username as customer_name, o.status, o.total_fee, o.shipping_fee, o.created_at, o.updated_at FROM orders o " +
            " JOIN public.customers c on o.customer_id = c.id " +
            " LIMIT :size " +
            " OFFSET :offset ")
    Flux<OrderShowListDTO> getListToShow(int offset, int size);


}
