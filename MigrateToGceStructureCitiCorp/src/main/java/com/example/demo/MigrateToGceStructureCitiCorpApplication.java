package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MigrateToGceStructureCitiCorpApplication  extends SpringBootServletInitializer{

	private static Class<MigrateToGceStructureCitiCorpApplication> applicationClass = MigrateToGceStructureCitiCorpApplication.class;
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MigrateToGceStructureCitiCorpApplication.class, args);
	}
	
}
