package com.code.thomasgregback.service.product;

import com.code.thomasgregback.dto.reports.TopFiveProductsDto;
import com.code.thomasgregback.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> findByActive(boolean active);

    List<TopFiveProductsDto> findTopFiveProducts();

    List<Product> getAll();
}
