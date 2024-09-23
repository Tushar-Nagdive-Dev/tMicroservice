package com.example.accountmswithpostgresql.service;

import com.example.accountmswithpostgresql.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {
    
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
