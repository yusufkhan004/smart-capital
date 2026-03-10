package com.v1.SmartCapital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SmartCapitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartCapitalApplication.class, args);
	}

}
