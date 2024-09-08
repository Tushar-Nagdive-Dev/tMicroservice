package com.example.trailsWithPostGresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
			title = "Accounts Microservices", 
			description = "Account application Using Postgresql",
			version = "v1",
			contact = @Contact(
				name = "Tushar Nagdive",
				email = "tushar79990@gmail.com",
				url = "https://www.linkedin.com/in/tushar-nagdive/"
			)
		)
	)
public class TrailsWithPostGresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailsWithPostGresqlApplication.class, args);
	}

}
