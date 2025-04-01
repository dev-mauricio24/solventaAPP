package com.solventa.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SolventaUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolventaUserApplication.class, args);
	}

}
