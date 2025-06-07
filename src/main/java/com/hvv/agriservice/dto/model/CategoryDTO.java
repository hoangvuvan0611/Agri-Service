package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
}
