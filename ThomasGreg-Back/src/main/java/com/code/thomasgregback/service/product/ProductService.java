package com.code.thomasgregback.service.product;

import com.code.thomasgregback.dto.reports.TopFiveProductsDto;
import com.code.thomasgregback.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findByActive(boolean active);

    List<TopFiveProductsDto> findTopFiveProducts();

    List<Product> getAll();

    Optional<Product> findById(Long id);
}
