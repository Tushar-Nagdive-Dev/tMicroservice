package com.example.accountmswithpostgresql.fallBack;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.accountmswithpostgresql.dto.CardsDto;
import com.example.accountmswithpostgresql.service.client.CardsFeignClient;

@Component
public class LoansFallBack implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
