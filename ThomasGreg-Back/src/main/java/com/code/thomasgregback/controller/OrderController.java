package com.code.thomasgregback.controller;

import com.code.thomasgregback.dto.OrderDetailsDto;
import com.code.thomasgregback.dto.OrderDto;
import com.code.thomasgregback.dto.ProductDto;
import com.code.thomasgregback.dto.ResponseDto;
import com.code.thomasgregback.entity.Order;
import com.code.thomasgregback.entity.OrderProduct;
import com.code.thomasgregback.entity.Product;
import com.code.thomasgregback.service.customer.CustomerService;
import com.code.thomasgregback.service.order.OrderProductService;
import com.code.thomasgregback.service.order.OrderService;
import com.code.thomasgregback.service.product.ProductService;
import com.code.thomasgregback.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thomasgreg/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    private OrderService orderService;
    private CustomerService customerService;
    private UserService userService;
    private ProductService productService;
    private OrderProductService orderProductService;

    @Autowired
    public void setOrderService(OrderService orderService) {this.orderService = orderService;}

    @Autowired
    public void setCustomerService(CustomerService customerService) {this.customerService = customerService;}

    @Autowired
    public void setUserService(UserService userService) {this.userService = userService;}

    @Autowired
    public void setProductService(ProductService productService) {this.productService = productService;}

    @Autowired
    public void setOrderProductService(OrderProductService orderProductService) {this.orderProductService = orderProductService;}

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

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody OrderDto body) {
        try {
            if (body.getProducts() != null) {
                Order order = Order.builder()
                        .number(body.getNumber())
                        .ecommerce(body.isEcommerce())
                        .outbound(body.isOutbound())
                        .random(body.isRandom())
                        .total(body.getTotal())
                        .customer(this.customerService.getById(body.getCustomer()).orElseThrow())
                        .user(this.userService.findById(body.getUser()).orElseThrow())
                        .build();

                Order orderSaved = this.orderService.save(order);

                for (ProductDto productDto : body.getProducts()) {
                    Product product = this.productService.findById(productDto.getId()).orElseThrow();

                    Long oldStock = product.getStock();
                    Long quantity = productDto.getQuantity();
                    Long newStock = orderSaved.isOutbound() ? oldStock - quantity : oldStock + quantity;

                    if (newStock < 0) {
                        return new ResponseEntity<>(new ResponseDto(false, "Stock insuficiente!", null), HttpStatus.BAD_REQUEST);
                    }
                    product.setStock(newStock);
                    this.productService.save(product);

                    OrderProduct orderProduct = OrderProduct.builder()
                            .order(orderSaved)
                            .product(product)
                            .quantity(productDto.getQuantity())
                            .total(productDto.getUnitPrice())
                            .build();

                    this.orderProductService.save(orderProduct);
                }

                return new ResponseEntity<>(new ResponseDto(true, "Orden creada!", orderSaved), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDto(false, "Orden sin productos!", null), HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al crear la orden!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
