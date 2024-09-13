package com.example.loanswithprostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.loanswithprostgresql.dto.LoanConfigDetailsDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title = "Loan Micorservice REST API Documentaion",
		description = "Big Bank Loans microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "Tushar Nagdive",
			email = "tushar79990@gmail.com",
			url = "https://www.linkedin.com/in/tushar-nagdive/"
		)
	),
	externalDocs = @ExternalDocumentation(
			description = "Big Bank Loans microservice REST API Documentation",
			url = "https://localhost:8003/swagger-ui.html"
	)
)
@EnableConfigurationProperties(LoanConfigDetailsDto.class)
public class LoanswithprostgresqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoanswithprostgresqlApplication.class, args);
	}

}
