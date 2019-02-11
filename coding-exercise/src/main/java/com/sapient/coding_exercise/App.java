/**
 * @author amanbansal
 *
 */
package com.sapient.coding_exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sapient.coding_exercise.controller.FileProcessorController;

@SpringBootApplication
@EnableAutoConfiguration
public class App implements CommandLineRunner
{
	@Autowired
	FileProcessorController controller;
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	logger.info( "Spring boot application is going down." );
    }

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application flow runner started.");
		controller.runApplicationFlow();
	}
}
