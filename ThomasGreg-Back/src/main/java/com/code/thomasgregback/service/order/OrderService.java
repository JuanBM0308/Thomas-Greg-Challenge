package com.code.thomasgregback.service.order;

import com.code.thomasgregback.dto.OrderDetailsDto;
import com.code.thomasgregback.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> discountedOrders();

    List<OrderDetailsDto> orderDetailsDtos(Long id);
}
