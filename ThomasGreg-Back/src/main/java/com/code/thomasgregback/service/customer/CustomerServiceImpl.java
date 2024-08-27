package com.code.thomasgregback.service.customer;

import com.code.thomasgregback.dto.reports.TopFiveCustomersDto;
import com.code.thomasgregback.dto.reports.TopFiveProductsDto;
import com.code.thomasgregback.entity.Customer;
import com.code.thomasgregback.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    protected CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<TopFiveCustomersDto> topFiveCustomers() {
        List<Object[]> queryResults = this.customerRepository.findTopFiveCustomers();
        List<TopFiveCustomersDto> topFiveCustomersDtos = new ArrayList<>();

        for (Object[] result : queryResults) {
            TopFiveCustomersDto topFiveCustomersDto = TopFiveCustomersDto.builder()
                    .id(Long.parseLong(result[0].toString()))
                    .name(result[1].toString())
                    .total(Long.parseLong(result[2].toString()))
                    .build();

            topFiveCustomersDtos.add(topFiveCustomersDto);
        }

        return topFiveCustomersDtos;
    }

    public Optional<Customer> getById(Long id) {
        return this.customerRepository.findById(id);
    }
}
