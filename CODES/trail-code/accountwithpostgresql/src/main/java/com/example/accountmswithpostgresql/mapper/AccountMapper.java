package com.example.accountmswithpostgresql.mapper;

import com.example.accountmswithpostgresql.dto.AccountDto;
import com.example.accountmswithpostgresql.entities.Accounts;

public class AccountMapper {
	
	public static AccountDto mapToAccountsDto(Accounts accounts, AccountDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
