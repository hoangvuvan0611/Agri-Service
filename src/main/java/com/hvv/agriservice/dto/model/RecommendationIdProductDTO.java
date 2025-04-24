package com.hvv.agriservice.dto.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class chi chua thong tin id cua product cua danh sach san pham goi y
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationIdProductDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
}
