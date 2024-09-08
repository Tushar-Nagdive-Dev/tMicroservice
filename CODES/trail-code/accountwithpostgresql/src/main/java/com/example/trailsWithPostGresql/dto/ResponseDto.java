package com.example.trailsWithPostGresql.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold successful response information")
public class ResponseDto {
	
	@Schema(description = "Status code in the response", example = "200")
	private String statusCode;
	
	@Schema(description = "Status Message of the response", example = "ex: Request Processed Successfully")
	private String statusMsg;
}
