package com.pragma.scheduleassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ScheduleAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleAssessmentApplication.class, args);
	}

}
