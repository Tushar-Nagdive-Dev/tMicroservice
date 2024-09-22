package com.example.accountmswithpostgresql.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "accounts")
@Setter
@Getter
public class AccountsContactInfoDto {
	
	private String message;
	
	private Map<String, String> contactDetails;
	
	private List<String>onCallSupport; 
	
	private String profile;
}
