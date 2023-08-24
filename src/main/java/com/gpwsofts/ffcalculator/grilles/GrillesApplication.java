package com.gpwsofts.ffcalculator.grilles;

import org.springframework.batch.core.Job;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class GrillesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrillesApplication.class, args);
	}
	
	

}
