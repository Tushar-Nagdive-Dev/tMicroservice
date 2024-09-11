package com.example.accountmswithpostgresql.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
	name = "Customer",
	description = "Schema to hold customer and account information"
)
public class CustomerDto {
	
	@Schema(
		description = "Name of Customer", example = "ex: John Cena"
	)
	@NotEmpty(message = "Name can not be null or empty")
	@Size(min = 3, max = 30, message = "The length of the customer name should be between 3 to 30")
    private String name;
	
	@Schema(
		description = "Email of Customer", example = "ex: JohnCena@mail.com"
	)
	@NotEmpty(message = "Email can not null or empty")
	private String email;
	
	@Schema(name = "Sell Number", example = "ex: XXXXXXXXXX")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
	private String mobileNumber;
	
	@Schema(description = "Account Details")
	private AccountDto accountDto;
}
