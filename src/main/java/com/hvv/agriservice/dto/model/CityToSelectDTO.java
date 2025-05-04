package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityToSelectDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String name;
}
