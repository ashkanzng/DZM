package com.dzm.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AppApplication {
	private static final Logger log = LoggerFactory.getLogger(AppApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
		log.info("--------- Open swagger page for further information --------");
		log.info("http://localhost:8080/swagger-ui/index.html#/company-resource");
	}
}
