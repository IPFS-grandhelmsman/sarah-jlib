package com.helmsman.sarah.node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SarahNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SarahNodeApplication.class, args);
	}

}

