package com.hvv.agriservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Doi tuong khach hang
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @Column("id")
    private UUID id;
    @Column("username")
    private String username;                // Ten su dung cua khach hang trong he thong
    @Column("firstname")
    private String firstName;               // Ho cua khach hang trong he thong
    @Column("lastname")
    private String lastName;                // Ten khach hang
    @Column("email")
    private String email;                   // Dia chi email
    @Column("password")
    private String password;                // Mat khau
    @Column("address")
    private String address;                 // Dia chi
    @Column("city_id")
    private String cityId;                    // Thanh pho
    @Column("district_id")
    private String districtId;                // Quan huyen
    @Column("ward_id")
    private UUID wardId;                          // Id xa phuong
    @Column("phone_number")
    private String phoneNumber;             // So dien thoai
    @Column("created_at")
    private LocalDateTime createdAt;        // Thoi gian khach hang dang ky
    @Column("updated_at")
    private LocalDateTime updatedAt;        // Thoi gian cap nhat thong tin khach hang
}
