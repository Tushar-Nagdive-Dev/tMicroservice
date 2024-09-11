package com.example.accountmswithpostgresql.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.accountmswithpostgresql.constants.AccountConstants;
import com.example.accountmswithpostgresql.dto.AccountDto;
import com.example.accountmswithpostgresql.dto.CustomerDto;
import com.example.accountmswithpostgresql.entities.Accounts;
import com.example.accountmswithpostgresql.entities.Customer;
import com.example.accountmswithpostgresql.exception.CustomerAlreadyExistsException;
import com.example.accountmswithpostgresql.exception.ResourceNotFoundException;
import com.example.accountmswithpostgresql.mapper.AccountMapper;
import com.example.accountmswithpostgresql.mapper.CustomerMapper;
import com.example.accountmswithpostgresql.repository.AccountRepo;
import com.example.accountmswithpostgresql.repository.CustomerRepo;

@Service("iAccountsService")
public class AccountServiceImpl implements IAccountsService{
	
	private AccountRepo accountRepo;
	
	private CustomerRepo customerRepo;
	
	public AccountServiceImpl(AccountRepo accountRepo, CustomerRepo customerRepo) {
		this.accountRepo = accountRepo;
		this.customerRepo = customerRepo;
	}

	@Override
	public void createAccount(CustomerDto customerDto) {
		Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
		Optional<Customer> optionalCustomer = this.customerRepo.findByMobileNumber(customer.getMobileNumber());
		if(optionalCustomer.isPresent())
			throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber " + customerDto.getMobileNumber());
		customer = this.customerRepo.save(customer);
		customer.setCreatedTime(LocalDateTime.now());
		customer.setModifiedTime(LocalDateTime.now());
		this.accountRepo.save(createNewAccount(customer));
	}
	
	private Accounts createNewAccount(Customer customer) {
		Accounts newAccount = new Accounts();
		newAccount.setCustomerId(customer.getId());
		newAccount.setCreatedTime(LocalDateTime.now());
		newAccount.setModifiedTime(LocalDateTime.now());
		newAccount.setAccountType(AccountConstants.SAVINGS);
		newAccount.setBranchAddress(AccountConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public List<Accounts> getAllAccounts() {
		return this.accountRepo.findAll();
	}
	
	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = this.customerRepo.findByMobileNumber(mobileNumber).orElseThrow( () -> 
					new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
				);
		
		Accounts account = this.accountRepo.findByCustomerId(customer.getId()).orElseThrow(() -> 
			new ResourceNotFoundException("Accounts", "customerId", customer.getId().toString())
		);
		
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
		
		 return customerDto; 
	}

	@Override
	public Boolean udpateAccountDetails(CustomerDto customerDto) {
		Boolean isUpdate = false;
		AccountDto accountDto = customerDto.getAccountDto();
		
		return null;
	}

	@Override
	public Boolean deleteAccount(String mobileNumber) {
		Customer customer =  this.customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
		);
		
		this.accountRepo.deleteByCustomerId(customer.getId());
		this.customerRepo.deleteById(customer.getId());
		return true;
	}
}
