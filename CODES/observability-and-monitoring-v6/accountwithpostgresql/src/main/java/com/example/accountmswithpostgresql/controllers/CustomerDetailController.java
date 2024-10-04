package com.example.accountmswithpostgresql.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accountmswithpostgresql.dto.CustomerDetailsDto;
import com.example.accountmswithpostgresql.dto.ErrorResponseDto;
import com.example.accountmswithpostgresql.service.ICustomerDetailsService;

import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@Validated
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "REST APIs Customers Microservices", description = "REST APIs for Big Bank details")
public class CustomerDetailController {

    private static final Logger log = LoggerFactory.getLogger(CustomerDetailController.class);

    private final ICustomerDetailsService iCustomerDetailsService;

    public CustomerDetailController(ICustomerDetailsService iCustomerDetailsService) {
        this.iCustomerDetailsService = iCustomerDetailsService;
    }
    
    @Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch Customer details based on a mobile number")
	@ApiResponses({
		@ApiResponse(responseCode = "200",description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500",description = "HTTP Status Internal Server Error",
			content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
		)
	})
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("bigBank-correlation-id") String correlationId, @RequestParam @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    String mobileNumber) {
        log.debug("BigBank-Correlation-id Found : {}", correlationId);
        CustomerDetailsDto customerDetailsDto = this.iCustomerDetailsService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);
    }
}
