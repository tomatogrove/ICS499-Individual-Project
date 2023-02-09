package com.team4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.team4.model"} )
public class BoardGameProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardGameProjectApplication.class, args);
	}

}
