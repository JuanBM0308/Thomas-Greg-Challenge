package com.code.thomasgregback.dto;

import com.code.thomasgregback.entity.Mark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private Date createdAt;
    private Mark mark;
    private Long purchasePrice;
    private Long salePrice;
    private String name;
    private Long stock;
    private boolean active;
}
