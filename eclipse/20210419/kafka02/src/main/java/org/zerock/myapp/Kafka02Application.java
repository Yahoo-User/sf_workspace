package org.zerock.myapp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;


@Log4j2

@SpringBootApplication
public class Kafka02Application {

	
	public static void main(String[] args) {
		log.debug("main({}) invoked.", Arrays.toString(args));
		
		SpringApplication.run(Kafka02Application.class, args);
	} // main

} // end class
