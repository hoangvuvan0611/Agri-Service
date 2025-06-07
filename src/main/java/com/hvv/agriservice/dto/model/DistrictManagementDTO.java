package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistrictManagementDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
    private String postalCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String city;
}
