package com.example.cardswithprostgresql.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "cards")
@Setter
@Getter
public class CardServiceInfoDto {
	
	private String message;
	
	private Map<List<String>, List<String>> cardDetails;
	
	private String bank;
	
	private String email;
	
	private String env;
}
