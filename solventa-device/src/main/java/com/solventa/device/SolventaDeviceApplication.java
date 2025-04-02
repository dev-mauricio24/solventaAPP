package com.solventa.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SolventaDeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolventaDeviceApplication.class, args);
	}

}
