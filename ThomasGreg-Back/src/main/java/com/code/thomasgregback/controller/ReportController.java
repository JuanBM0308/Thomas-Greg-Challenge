package com.code.thomasgregback.controller;

import com.code.thomasgregback.dto.ResponseDto;
import com.code.thomasgregback.dto.reports.TopFiveCustomersDto;
import com.code.thomasgregback.dto.reports.TopFiveProductsDto;
import com.code.thomasgregback.entity.Product;
import com.code.thomasgregback.service.customer.CustomerService;
import com.code.thomasgregback.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/thomasgreg/report")
public class ReportController {
    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get/active/products")
    public ResponseEntity<?> activeProducts() {
        try {
            List<Product> productList = this.productService.findByActive(true);
            return new ResponseEntity<>(new ResponseDto(true, "Productos encontrados con exito!", productList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al recuperar los procutos activos!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/top/products")
    public ResponseEntity<?> getReportProducts() {
        try {
            List<TopFiveProductsDto> topFiveProductsDtos = this.productService.findTopFiveProducts();

            if (topFiveProductsDtos.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No hay datos!", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(true, "Reporte generado con exito!", topFiveProductsDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al generar el reporte!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/top/customers")
    public ResponseEntity<?> getReportCustomers() {
        try {
            List<TopFiveCustomersDto> topFiveCustomersDtos = this.customerService.topFiveCustomers();

            if (topFiveCustomersDtos.isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No hay datos!", null), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(new ResponseDto(true, "Reporte generado con exito!", topFiveCustomersDtos), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al generar el reporte!", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
