package com.piyush.healthsync.healthsync_hospital_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class HealthsyncHospitalManagementSystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(HealthsyncHospitalManagementSystemApplication.class, args);
	}

	@GetMapping
	public String greet() {
		return "Hello World!";
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


}
