package com.example.cardswithprostgresql.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cards")
public record CardServiceInfoDto(String message, Map<List<String>, List<String>> cardDetails, String bank, String email, String env) {

}
