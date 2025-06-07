package com.hvv.agriservice.dto.request;

import com.hvv.agriservice.dto.model.OrderItemDTO;
import com.hvv.agriservice.dto.model.ShippingInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private ShippingInfo shippingInfo;
    private String paymentMethod;
    private List<OrderItemDTO> orderItemList;
    private String totalAmount;
}
