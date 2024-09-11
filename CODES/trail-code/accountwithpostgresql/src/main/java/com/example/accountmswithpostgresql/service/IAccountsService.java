package com.example.accountmswithpostgresql.service;

import java.util.List;

import com.example.accountmswithpostgresql.dto.CustomerDto;
import com.example.accountmswithpostgresql.entities.Accounts;


public interface IAccountsService {
	
	void createAccount(CustomerDto customerDto);
	
	List<Accounts> getAllAccounts();
	
	CustomerDto fetchAccount(String mobileNumber) ;
	
	Boolean udpateAccountDetails(CustomerDto customerDto);
	
	Boolean deleteAccount(String mobileNumber);
}
