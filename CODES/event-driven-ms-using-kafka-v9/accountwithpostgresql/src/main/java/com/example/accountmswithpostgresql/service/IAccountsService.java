package com.example.accountmswithpostgresql.service;

import java.util.List;

import com.example.accountmswithpostgresql.dto.CustomerDto;
import com.example.accountmswithpostgresql.entities.Accounts;


public interface IAccountsService {
	
	void createAccount(CustomerDto customerDto);
	
	List<Accounts> getAllAccounts();
	
	CustomerDto fetchAccount(String mobileNumber) ;
	
	/**
    *
    * @param customerDto - CustomerDto Object
    * @return boolean indicating if the update of Account details is successful or not
    */
   Boolean updateAccount(CustomerDto customerDto);
	
	Boolean deleteAccount(String mobileNumber);

    Boolean updateCommunicationStatus(Long accountNumber);
}
