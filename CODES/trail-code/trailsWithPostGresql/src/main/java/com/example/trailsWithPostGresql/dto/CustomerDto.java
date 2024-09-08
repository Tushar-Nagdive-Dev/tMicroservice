package com.example.trailsWithPostGresql.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
	
	@NotEmpty(message = "Name can not be null or empty")
	@Size(min = 3, max = 30, message = "The length of the customer name should be between 3 to 30")
    private String name;
	
	@NotEmpty(message = "Email can not null or empty")
	private String email;
	
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
	private String mobileNumber;
	
	private AccountDto accountDto;
}
