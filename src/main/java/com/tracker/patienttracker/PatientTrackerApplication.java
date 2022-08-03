package com.tracker.patienttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
public class PatientTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientTrackerApplication.class, args);
	}

}
