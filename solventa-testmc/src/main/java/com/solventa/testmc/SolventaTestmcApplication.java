package com.solventa.testmc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SolventaTestmcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolventaTestmcApplication.class, args);
	}

}
