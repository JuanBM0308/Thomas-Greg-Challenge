package com.code.thomasgregback.service.order;

import com.code.thomasgregback.dto.OrderDetailsDto;
import com.code.thomasgregback.dto.OrderDto;
import com.code.thomasgregback.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDto> discountedOrders();

    List<OrderDetailsDto> orderDetailsDtos(Long id);

    Order save(Order order);
}
