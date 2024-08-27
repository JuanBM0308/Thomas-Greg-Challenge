package com.code.thomasgregback.service.customer;

import com.code.thomasgregback.dto.reports.TopFiveCustomersDto;
import com.code.thomasgregback.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<TopFiveCustomersDto> topFiveCustomers();

    Optional<Customer> getById(Long id);
}
