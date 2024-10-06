package com.bbank.gatewayserver.controller;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class FallBackController {
    
    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error occurred. Please try after some time or contact suppoer team !!");
    }
    
}
