package com.ktymty.gamescatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GamesCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesCatalogServiceApplication.class, args);
    }

}
