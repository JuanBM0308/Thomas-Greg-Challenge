package com.code.thomasgregback.dto;

import com.code.thomasgregback.entity.Order;
import com.code.thomasgregback.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDto {
    private Long id;
    private Date createdAt;
    private Order order;
    private Product product;
    private Long quantity;
    private Long total;
}
