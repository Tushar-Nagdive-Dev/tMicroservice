package com.example.accountmswithpostgresql.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String, String> contactDetails, List<String>onCallSupport, String profile) {

}
