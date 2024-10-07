package com.example.accountmswithpostgresql.functions;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.accountmswithpostgresql.service.IAccountsService;

@Configuration
public class AccountFunctions {
    
    private static final Logger log = LoggerFactory.getLogger(AccountFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountsService iAccountsService) {
        return accountNumner -> {
            log.info("Updating Communication status for the acount number : {}", accountNumner.toString());
            iAccountsService.updateCommunicationStatus(accountNumner);
        };
    }
}
