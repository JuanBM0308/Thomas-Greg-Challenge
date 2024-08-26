package com.code.thomasgregback.service.customer;

import com.code.thomasgregback.dto.reports.TopFiveCustomersDto;

import java.util.List;

public interface CustomerService {
    List<TopFiveCustomersDto> topFiveCustomers();
}
