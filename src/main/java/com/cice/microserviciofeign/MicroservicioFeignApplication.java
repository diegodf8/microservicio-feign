package com.cice.microserviciofeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaServer
@EnableFeignClients
@SpringBootApplication
public class MicroservicioFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioFeignApplication.class, args);
	}
}
