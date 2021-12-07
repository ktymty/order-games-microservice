package com.ktymty.gamesnotificationservice;

import com.ktymty.gamesnotificationservice.service.EmailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
public class GamesNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesNotificationServiceApplication.class, args);
	}

	// Producer -> return Type = Supplier => Output Binder
	// Producer&Consumer -> return Type = Function => Input & Output Binder
	@Bean
	public Consumer<Message<String>> notificationEventSupplier() {
		return message -> new EmailSender().sendEmail(message.getPayload());
	}
}
