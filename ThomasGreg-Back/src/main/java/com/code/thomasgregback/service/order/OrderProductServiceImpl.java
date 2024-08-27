package com.code.thomasgregback.service.order;

import com.code.thomasgregback.entity.OrderProduct;
import com.code.thomasgregback.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService{
    protected OrderProductRepository orderProductRepository;

    @Autowired
    public void setOrderProductRepository(OrderProductRepository orderProductRepository) {this.orderProductRepository = orderProductRepository;}

    public OrderProduct save(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
