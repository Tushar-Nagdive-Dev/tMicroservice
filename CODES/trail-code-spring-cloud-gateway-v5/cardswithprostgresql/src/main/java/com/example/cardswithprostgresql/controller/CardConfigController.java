package com.example.cardswithprostgresql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.cardswithprostgresql.dto.CardServiceInfoDto;
import com.example.cardswithprostgresql.dto.ErrorResponseDto;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "CRUD REST APIs for Cards Configd",
        description = "Get all Cards major configuration"
)
@RestController
@RequestMapping(path = "/config", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardConfigController {

	@Value("${build.name}")
	private String buildName;
	
	@Value("${build.version}")	
	private String buildVersion;
	
	@Autowired
	private CardServiceInfoDto cardServiceInfoDto;
	
	@Operation(summary = "Get Build information", description = "Get build informations that is deployed into cards microservices")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(
			schema = @Schema(implementation = ErrorResponseDto.class)
		))
	})

	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildVersion() {
		return ResponseEntity.status(HttpStatus.OK).body("Build Name: " + buildName + ", Build Version: " + buildVersion);
	}
	
	@Operation(summary = "Get Cards Info", description = "Get Cards informations that is deployed into card microservices")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error", content = @Content(
			schema = @Schema(implementation = ErrorResponseDto.class)
		))
	})

	@RateLimiter(name = "getContactInfo", fallbackMethod = "getContactInfoFallBack")
	@GetMapping("/card-info")
	public ResponseEntity<CardServiceInfoDto> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(cardServiceInfoDto);
	}

	public ResponseEntity<CardServiceInfoDto> getContactInfoFallBack(Throwable throwable) {
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
