package com.bbank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator bigBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes().route(p -> p.path("/bigBank/accounts/**")
			.filters(f -> f.rewritePath("/bigBank/accounts/(?<segment>.*)","/${segment}")
				.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
				.circuitBreaker(config -> config.setName("accountsCircuitBreaker")))
			.uri("lb://ACCOUNTS"))
			.route(p -> p.path("/bigBank/loans/**")
			.filters(f -> f.rewritePath("/bigBank/loans/(?<segment>.*)", "/${segment}")
				.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
			.uri("lb://LOANS"))
			.route(p -> p.path("/bigBank/cards/**")
			.filters(f -> f.rewritePath("/bigBank/cards/(?<segment>.*)", "/${segment}")
				.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
			.uri("lb://CARDS")).build();
	}
}
