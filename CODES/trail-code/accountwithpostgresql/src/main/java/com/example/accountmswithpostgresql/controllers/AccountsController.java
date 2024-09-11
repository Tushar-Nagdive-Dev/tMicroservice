package com.example.accountmswithpostgresql.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accountmswithpostgresql.constants.AccountConstants;
import com.example.accountmswithpostgresql.dto.AccountDto;
import com.example.accountmswithpostgresql.dto.AccountsContactInfoDto;
import com.example.accountmswithpostgresql.dto.CustomerDto;
import com.example.accountmswithpostgresql.dto.ErrorResponseDto;
import com.example.accountmswithpostgresql.dto.ResponseDto;
import com.example.accountmswithpostgresql.entities.Accounts;
import com.example.accountmswithpostgresql.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
	name = "CRUD REST APIs Accounts Microservices",
	description = "CRUD REST APIs for CREATE, REMOVE, UPDATE, DELETE"
)
public class AccountsController {
	
	private IAccountsService iAccountsService;
	
	/* basic approach Starts */
	@Value("${build.version}")
	private String buildVersion;
	
	@Autowired
	private Environment environment;
	/* basic approach End */
	
	/* Advance approach starts*/
	@Autowired
	private AccountsContactInfoDto accountsContactsInfo;
	/* Advance approach end*/
	
	@Autowired
	public void setIAccountsService(@Qualifier("iAccountsService") IAccountsService iAccountsService) {
		this.iAccountsService = iAccountsService;
	}

	@Operation(
		summary = "Create Account REST API",
		description = "Add New Account"
	)
	@ApiResponse(
		responseCode = "201",
		description = "HTTP Status CREATED"
	)
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		iAccountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetchAllAccounts")
	public List<AccountDto> getAllAccounts() {
		List<Accounts> accounts = iAccountsService.getAllAccounts();
		List<AccountDto> accoutDtolist = new ArrayList<>();
		for(Accounts account: accounts) {
			AccountDto accountDto = new AccountDto();
			accountDto.setAccountType(account.getAccountType());
			accountDto.setBranchAddress(account.getBranchAddress());
			accoutDtolist.add(accountDto);
		}
		
		return accoutDtolist;
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
		CustomerDto customerDto = this.iAccountsService.fetchAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	@DeleteMapping("/delete")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
			content = @Content(
				schema = @Schema(implementation = ErrorResponseDto.class)
			)
		)
	})
	public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam String mobileNumber) {
		Boolean isDeleted = this.iAccountsService.deleteAccount(mobileNumber);
		if(Boolean.TRUE.equals(isDeleted)) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSATE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
		}
	}
	
	@Operation(summary = "Get Build information", description = "Get build informations that is deployed into account microservices")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(
			schema = @Schema(implementation = ErrorResponseDto.class)
		))
	})
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
	}
	
	@Operation(summary = "Get JAVA Version", description = "Get Java version informations that is deployed into account microservices")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(
			schema = @Schema(implementation = ErrorResponseDto.class)
		))
	})
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion() {
		return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
	}
	
	@Operation(summary = "Get Accounts Info", description = "Get Account contact informations that is deployed into account microservices")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(
			schema = @Schema(implementation = ErrorResponseDto.class)
		))
	})
	@GetMapping("/contact-info")
	public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(accountsContactsInfo);
	}
}
