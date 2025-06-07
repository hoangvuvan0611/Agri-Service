package com.hvv.agriservice.dto.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfo {
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String district;
    private String ward;
    private String note;
}
