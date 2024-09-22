package com.example.loanswithprostgresql.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "loan")
@Getter
@Setter
public class LoanConfigDetailsDto {

	private String message;
	
	private Map<List<String>, List<String>> loanDetails;
	
	private String bank;
	
	private String email;
	
	private String env;
}