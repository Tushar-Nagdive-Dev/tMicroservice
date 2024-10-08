package com.bigBank.message.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bigBank.message.dto.AccountMessageDto;

@Configuration
public class MessageFunction {
    
    private static final Logger log = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<AccountMessageDto, AccountMessageDto> email() {
        return accountsMsgDto -> {
            log.info("Sending email with the details : " + accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountMessageDto, Long> sms() {
        return accountsMsgDto -> {
            log.info("Sending sms with the details : " + accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }
}
