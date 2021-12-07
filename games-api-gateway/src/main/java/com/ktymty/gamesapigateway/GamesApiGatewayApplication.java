package com.ktymty.gamesapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
public class GamesApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GamesApiGatewayApplication.class, args);
    }
}
