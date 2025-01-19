package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

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
    private UUID id;
    @Column("customer_id")
    private UUID customerId;                    // Ma khach hang
    @Column("phone_number")
    private String phoneNumber;                 // So dien thoai cua khach hang
    @Column("address")
    private String address;                     // Dia chi giao hang cu the
    @Column("city_id")
    private UUID cityId;                        // Id thanh pho cua dia chi giao hang
    @Column("district_id")
    private UUID districtId;                    // Id quan huyen cua khach hang
    @Column("ward_id")
    private UUID wardId;                          // Id xa phuong
}
