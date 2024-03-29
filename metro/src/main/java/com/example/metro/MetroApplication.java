package com.example.metro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetroApplication.class, args);
	}

}
