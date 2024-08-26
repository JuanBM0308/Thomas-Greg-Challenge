package com.code.thomasgregback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
}
