package com.example.trailsWithPostGresql.mapper;

import com.example.trailsWithPostGresql.dto.AccountDto;
import com.example.trailsWithPostGresql.entities.Accounts;

public class AccountMapper {
	
	public static AccountDto mapToAccountDto(Accounts accounts, AccountDto accountDto) {
		accountDto.setAccountType(accounts.getAccountType());
		accountDto.setBranchAddress(accounts.getBranchAddress());
		return accountDto;
	}
	
	public static Accounts mapToAccount(Accounts accounts, AccountDto accountDto) {
		accounts.setAccountType(accountDto.getAccountType());
		accounts.setBranchAddress(accountDto.getBranchAddress());
		return accounts;
	}
}
