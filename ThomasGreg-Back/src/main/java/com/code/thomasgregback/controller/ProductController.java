package com.code.thomasgregback.controller;

import com.code.thomasgregback.dto.ResponseDto;
import com.code.thomasgregback.entity.Product;
import com.code.thomasgregback.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/thomasgreg/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {this.productService = productService;}

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            List<Product> productList = this.productService.getAll();
            return new ResponseEntity<>(new ResponseDto(true, "Productos encontrados con exito", productList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al recuperar los productos", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
