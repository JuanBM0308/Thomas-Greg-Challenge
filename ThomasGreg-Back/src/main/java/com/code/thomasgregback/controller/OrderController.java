package com.code.thomasgregback.controller;

import com.code.thomasgregback.dto.OrderDetailsDto;
import com.code.thomasgregback.dto.OrderDto;
import com.code.thomasgregback.dto.ResponseDto;
import com.code.thomasgregback.entity.Product;
import com.code.thomasgregback.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/thomasgreg/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/get/discounts")
    public ResponseEntity<?> discounts() {
        try {
            List<OrderDto> orderDtoList = this.orderService.discountedOrders();
            return new ResponseEntity<>(new ResponseDto(true, "Ordenes encontrados con exito!", orderDtoList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al recuperar las ordenes!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/details/{id}")
    public ResponseEntity<?> orderDetails(@PathVariable Long id) {
        try {
            List<OrderDetailsDto> orderDetailsDtos = this.orderService.orderDetailsDtos(id);
            return new ResponseEntity<>(new ResponseDto(true, "Detalle de la orden!", orderDetailsDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al obtener los detalles de la orden!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
