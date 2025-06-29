package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hvv.agriservice.constant.enums.OrderStatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String status;                 // Trang thai cua don hang
    private BigDecimal shippingFee;                 // Phí giao hàng
    private BigDecimal totalFee;                    // Tổng tiền thanh toán
    private LocalDateTime createdAt;                // Thoi gian tao don hang
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private List<OrderItemDTO> orderItemList;
    private CustomerDTO customer;
}
