package com.hvv.agriservice.dto.request;

import com.hvv.agriservice.dto.model.OrderItem;
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
    private List<OrderItem> orderItemList;
    private String totalAmount;
}
