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
    @Column("first_name")
    private String firstName;               // Ho cua khach hang trong he thong
    @Column("last_name")
    private String lastName;                // Ten khach hang
    @Column("email")
    private String email;                   // Dia chi email
    @Column("password")
    private String password;                // Mat khau
    @Column("address")
    private String address;                 // Dia chi
    @Column("city")
    private String city;                    // Thanh pho
    @Column("district")
    private String district;                // Quan huyen
    @Column("postal_code")
    private String postalCode;              // Ma buu dien cua khach hang
    @Column("phone_number")
    private String phoneNumber;             // So dien thoai
    @Column("created_at")
    private LocalDateTime createdAt;        // Thoi gian khach hang dang ky
    @Column("updated_at")
    private LocalDateTime updatedAt;        // Thoi gian cap nhat thong tin khach hang
}
