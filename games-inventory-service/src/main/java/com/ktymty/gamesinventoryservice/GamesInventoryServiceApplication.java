package com.ktymty.gamesinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GamesInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesInventoryServiceApplication.class, args);
    }

}
