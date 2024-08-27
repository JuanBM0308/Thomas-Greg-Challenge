package com.code.thomasgregback.service.product;

import com.code.thomasgregback.dto.reports.TopFiveProductsDto;
import com.code.thomasgregback.entity.Product;
import com.code.thomasgregback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    protected ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByActive(boolean active) {
        return this.productRepository.findByActive(active);
    }

    public List<TopFiveProductsDto> findTopFiveProducts() {
        List<Object[]> queryResults = this.productRepository.findTopFiveProducts();
        List<TopFiveProductsDto> topFiveProductsDtos = new ArrayList<>();

        for (Object[] result : queryResults) {
            TopFiveProductsDto topFiveProductsDto = TopFiveProductsDto.builder()
                    .id(Long.parseLong(result[0].toString()))
                    .name(result[1].toString())
                    .total(Long.parseLong(result[2].toString()))
                    .build();

            topFiveProductsDtos.add(topFiveProductsDto);
        }

        return topFiveProductsDtos;
    }

    public List<Product> getAll() {return this.productRepository.findAll();}

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }
}
