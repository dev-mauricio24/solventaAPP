package com.solventa.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SolventaConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolventaConfigApplication.class, args);
	}

}
