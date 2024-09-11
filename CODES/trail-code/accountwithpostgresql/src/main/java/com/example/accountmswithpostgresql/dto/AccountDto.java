package com.example.accountmswithpostgresql.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema for Account information")
public class AccountDto {
	
	@Schema(description = "Account Type of Bank account", example = "ex: Saving")
	@NotEmpty(message = "Account Type can not be null or empty")
	private String accountType;
	
	@Schema(name = "Bank Address", description = "Bank Address where account belongs")
	@NotEmpty(message = "Branch Address can not be a null or empty")
	private String branchAddress;
}
