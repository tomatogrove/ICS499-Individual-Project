package com.team4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan( "com.team4.*" )
@EnableJpaRepositories("com.team4.*")
@ComponentScan( basePackages = { "com.team4.*" } )
public class BoardGameProjectApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BoardGameProjectApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String[] domains = {"http://localhost:4200", "http://localhost:8080"};
				registry.addMapping("/**").allowedMethods("*").allowedOrigins(domains);
			}
		};
	}

}