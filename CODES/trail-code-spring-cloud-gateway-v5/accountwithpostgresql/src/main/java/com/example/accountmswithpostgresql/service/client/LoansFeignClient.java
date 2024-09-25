package com.example.accountmswithpostgresql.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.accountmswithpostgresql.dto.LoansDto;

@FeignClient("loans")
public interface LoansFeignClient {
    
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("bigBank-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
