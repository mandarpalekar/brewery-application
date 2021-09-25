package com.mandar.breweryapplication.mappers;

import com.mandar.breweryapplication.domain.Customer;
import com.mandar.breweryapplication.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
