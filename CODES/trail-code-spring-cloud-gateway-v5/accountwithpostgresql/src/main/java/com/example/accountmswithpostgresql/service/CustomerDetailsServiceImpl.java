package com.example.accountmswithpostgresql.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.accountmswithpostgresql.dto.AccountDto;
import com.example.accountmswithpostgresql.dto.CardsDto;
import com.example.accountmswithpostgresql.dto.CustomerDetailsDto;
import com.example.accountmswithpostgresql.dto.LoansDto;
import com.example.accountmswithpostgresql.entities.Accounts;
import com.example.accountmswithpostgresql.entities.Customer;
import com.example.accountmswithpostgresql.exception.ResourceNotFoundException;
import com.example.accountmswithpostgresql.mapper.AccountMapper;
import com.example.accountmswithpostgresql.mapper.CustomerMapper;
import com.example.accountmswithpostgresql.repository.AccountRepo;
import com.example.accountmswithpostgresql.repository.CustomerRepo;
import com.example.accountmswithpostgresql.service.client.CardsFeignClient;
import com.example.accountmswithpostgresql.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImpl implements ICustomerDetailsService{

    private AccountRepo accountRepo;

    private CustomerRepo customerRepo;

    private CardsFeignClient cardsFeignClient;

    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = this.customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = this.accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountsDto(accounts, new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
    
}
