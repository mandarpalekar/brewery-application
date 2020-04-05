package com.mandar.breweryapplication.service;

import com.mandar.breweryapplication.model.CustomerDto;
import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteBeerById(UUID customerId);
}
