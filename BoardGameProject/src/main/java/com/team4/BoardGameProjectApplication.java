package com.team4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan( basePackages = {"com.team4.model.*"} )
@EnableJpaRepositories("com.team4.model.*")
@ComponentScan( basePackages = { "com.team4.model.*" } )
public class BoardGameProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardGameProjectApplication.class, args);
	}

}
