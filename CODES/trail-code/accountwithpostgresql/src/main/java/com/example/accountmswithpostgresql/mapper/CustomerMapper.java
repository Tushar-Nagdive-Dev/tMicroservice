package com.example.accountmswithpostgresql.mapper;

import com.example.accountmswithpostgresql.dto.CustomerDto;
import com.example.accountmswithpostgresql.entities.Customer;

public class CustomerMapper {
	
	public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		return customerDto;
	}
	
	public static Customer mapToCustomer(Customer customer, CustomerDto customerDto) {
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}
}
