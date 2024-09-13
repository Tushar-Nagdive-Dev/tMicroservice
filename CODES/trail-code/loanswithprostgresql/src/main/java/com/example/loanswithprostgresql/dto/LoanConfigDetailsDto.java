package com.example.loanswithprostgresql.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.swagger.v3.oas.annotations.tags.Tag;

@ConfigurationProperties(prefix = "loan")
public record LoanConfigDetailsDto(String message, Map<List<String>, List<String>> loanDetails, String bank, String email, String env) {

}