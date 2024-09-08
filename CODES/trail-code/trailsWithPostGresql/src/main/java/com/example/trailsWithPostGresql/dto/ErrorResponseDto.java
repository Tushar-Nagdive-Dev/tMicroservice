package com.example.trailsWithPostGresql.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Error Response", description = "Schema to hold error response information")
public class ErrorResponseDto {
	
	@Schema(description = "API Path invoked by client")
	private String apiPath;
	
	@Schema(description = "Error Code")
	private HttpStatus errorCode;
	
	@Schema(description = "Error Message")
	private String errorMessage;
	
	@Schema(description = "Error Time")
	private LocalDateTime errorTime;
}
