package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;                // Ten su dung cua khach hang trong he thong     // Ten khach hang
    private String email;                   // Dia chi email
    private String address;                 // Dia chi
    private Long cityId;                    // Thanh pho
    private Long districtId;                // Quan huyen
    private Long wardId;                    // Id xa phuong
    private String phoneNumber;             // So dien thoai
    private LocalDateTime createdAt;        // Thoi gian khach hang dang ky
    private LocalDateTime updatedAt;        // Thoi gian cap nhat thong tin khach hang
}
