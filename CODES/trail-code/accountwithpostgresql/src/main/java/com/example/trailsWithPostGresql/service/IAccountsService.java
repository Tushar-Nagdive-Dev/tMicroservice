package com.example.trailsWithPostGresql.service;

import java.util.List;

import com.example.trailsWithPostGresql.dto.CustomerDto;
import com.example.trailsWithPostGresql.entities.Accounts;

public interface IAccountsService {
	
	void createAccount(CustomerDto customerDto);
	
	List<Accounts> getAllAccounts();
	
	CustomerDto fetchAccount(String mobileNumber) ;
	
	Boolean udpateAccountDetails(CustomerDto customerDto);
	
	Boolean deleteAccount(String mobileNumber);
}
