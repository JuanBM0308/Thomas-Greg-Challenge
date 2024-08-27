package com.code.thomasgregback.service.order;

import com.code.thomasgregback.entity.OrderProduct;

import java.util.Optional;

public interface OrderProductService {
    OrderProduct save(OrderProduct orderProduct);
}
