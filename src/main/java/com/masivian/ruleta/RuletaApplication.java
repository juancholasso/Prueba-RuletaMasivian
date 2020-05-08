package com.masivian.ruleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan("com.masivian.beans")
@ComponentScan("com.masivian.controllers")
@ComponentScan("com.masivian.repositories")
@ComponentScan("com.masivian.services")
@ComponentScan("com.masivian.models")
@ComponentScan("com.masivian.configurations")
@ComponentScan("com.masivian.middlewares")
@EnableAutoConfiguration
@RestController
public class RuletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuletaApplication.class, args);
	}

}
