package com.code.thomasgregback.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopFiveProductsDto {
    private Long id;
    private String name;
    private Long total;
}
