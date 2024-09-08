package com.example.trailsWithPostGresql.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountDto {
	
	@NotEmpty(message = "Account Type can not be null or empty")
	private String accountType;
	
	@NotEmpty(message = "Branch Address can not be a null or empty")
	private String branchAddress;
}
