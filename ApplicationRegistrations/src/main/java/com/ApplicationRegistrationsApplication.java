package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationRegistrationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationRegistrationsApplication.class, args);
	}

}
