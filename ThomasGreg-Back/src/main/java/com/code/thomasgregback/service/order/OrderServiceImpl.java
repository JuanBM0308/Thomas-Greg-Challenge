package com.code.thomasgregback.service.order;

import com.code.thomasgregback.dto.OrderDetailsDto;
import com.code.thomasgregback.dto.OrderDto;
import com.code.thomasgregback.entity.Order;
import com.code.thomasgregback.repository.CustomerRepository;
import com.code.thomasgregback.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    protected OrderRepository orderRepository;
    protected CustomerRepository customerRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    public List<OrderDto> discountedOrders() {
        List<Order> orderList = this.orderRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startRange = LocalDateTime.parse("2024-08-23 21:55:00", formatter);
        LocalDateTime endRange = LocalDateTime.parse("2024-08-23 22:40:00", formatter);

        List<Object[]> queryResults = this.customerRepository.findTopFiveCustomers();
        List<Integer> frequentCustomers = new ArrayList<>();
        for (Object[] result : queryResults) {
            int customerIdTopFive = Integer.parseInt(result[0].toString());
            frequentCustomers.add(customerIdTopFive);
        }

        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orderList) {
            double total = order.getTotal();

            String createdAtString = order.getCreatedAt().toString();
            // Limpiar la cadena para eliminar fracciones de segundos
            if (createdAtString.contains(".")) {
                createdAtString = createdAtString.split("\\.")[0]; // Eliminar fracciones de segundos
            }
            LocalDateTime createdAt = LocalDateTime.parse(createdAtString, formatter);

            // Caso A: Descuento del 10% por rango en tiempo
            if (createdAt.isAfter(startRange) && createdAt.isBefore(endRange)) {
                total -= total * 0.1;

                // Caso B: Descuento adicional del 50% si es aleatorio por rango de tiempo
                if (order.isRandom()) {
                    total -= total * 0.5;
                }
            }

            int customerId = Math.toIntExact(order.getCustomer().getId());

            // Caso C: Descuento adicional del 5% si es cliente frecuente
            if (frequentCustomers.contains(customerId)) {
                total -= total * 0.05;
            }

            OrderDto orderDto = OrderDto.builder()
                    .id(order.getId())
                    .createdAt(order.getCreatedAt())
                    .number(order.getNumber())
                    .ecommerce(order.isEcommerce())
                    .outbound(order.isOutbound())
                    .random(order.isRandom())
                    .total(order.getTotal())
                    .totalWithDiscount((long) Math.round(total))
                    .customer(order.getCustomer().getId())
                    .completeNameCustomer(order.getCustomer().getName() + " " + order.getCustomer().getLastname())
                    .user(order.getUser().getId())
                    .completeName(order.getUser().getName() + " " + order.getUser().getLastname())
                    .build();

            orderDtos.add(orderDto);
        }

        return orderDtos; // Devolver la lista de órdenes con los totales actualizados
    }

    public List<OrderDetailsDto> orderDetailsDtos(Long id) {
        List<Object[]> queryResults = this.orderRepository.findOrderDetails(id);
        List<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();

        for (Object[] results : queryResults) {
            OrderDetailsDto orderDetailsDto = OrderDetailsDto.builder()
                    .name(results[0].toString())
                    .unitPrice(Long.parseLong(results[1].toString()))
                    .quantity(Long.parseLong(results[2].toString()))
                    .total(Long.parseLong(results[3].toString()))
                    .build();

            orderDetailsDtos.add(orderDetailsDto);
        }

        return orderDetailsDtos;
    }
}
