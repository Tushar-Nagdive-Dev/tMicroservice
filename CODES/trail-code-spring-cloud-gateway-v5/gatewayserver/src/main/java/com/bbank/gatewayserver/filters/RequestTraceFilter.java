package com.bbank.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter{
    
    private static final Logger log = LoggerFactory.getLogger(RequestTraceFilter.class);

    private final FilterUtility filterUtility;

    public RequestTraceFilter(FilterUtility filterUtility) {
        this.filterUtility = filterUtility;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeaders)) {
            log.debug("bigBank-correlation-id found in RequestTraceFilter : {}");

        } else {
            String correlationId = generateCorrelationId();
            exchange = this.filterUtility.setCorrelationId(exchange, correlationId);
            log.debug("bigBank-correlation-id generated in RequestTraceFilter : {}");
        }
        return chain.filter(exchange);
    }

    private Boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if(this.filterUtility.getCorrelationId(requestHeaders) != null)
            return true;
        else 
            return false;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
