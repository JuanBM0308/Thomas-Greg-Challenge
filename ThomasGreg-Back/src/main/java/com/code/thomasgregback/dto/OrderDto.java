package com.code.thomasgregback.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Date createdAt;
    private String number;
    private boolean ecommerce;
    private boolean outbound;
    private boolean random;
    private Long total;
    private Long totalWithDiscount;
    private Long customer;
    private String completeNameCustomer;
    private Long user;
    private String completeName;

    @Singular
    private List<ProductDto> products;

    private Integer countProducts;
}
