package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Doi tuong chua thong tin giao hang cua khach hang
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_info")
public class DeliveryInfo {
    @Id
    @Column("id")
    private Long id;
    @Column("customer_id")
    private Long customerId;                    // Ma khach hang
    @Column("phone_number")
    private String phoneNumber;                 // So dien thoai cua khach hang
    @Column("address")
    private String address;                     // Dia chi giao hang cu the
    @Column("city_id")
    private Long cityId;                        // Id thanh pho cua dia chi giao hang
    @Column("district_id")
    private Long districtId;                    // Id quan huyen cua khach hang
    @Column("ward_id")
    private Long wardId;                          // Id xa phuong
}
