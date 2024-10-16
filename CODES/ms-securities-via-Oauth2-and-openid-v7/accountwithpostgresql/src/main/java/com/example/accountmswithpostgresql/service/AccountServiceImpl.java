package com.example.accountmswithpostgresql.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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

	/**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = this.customerRepo.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer = this.customerRepo.save(customer);
        this.accountRepo.save(createNewAccount(savedCustomer));
    }

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

	@Override
	public List<Accounts> getAllAccounts() {
		return this.accountRepo.findAll();
	}
	
	/**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = this.customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = this.accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountsDto(accounts, new AccountDto()));
        return customerDto;
    }


	@Override
    public Boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = this.accountRepo.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = this.customerRepo.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            this.customerRepo.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

	/**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public Boolean deleteAccount(String mobileNumber) {
        Customer customer = this.customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        this.accountRepo.deleteByCustomerId(customer.getCustomerId());
        this.customerRepo.deleteById(customer.getCustomerId());
        return true;
    }
}
