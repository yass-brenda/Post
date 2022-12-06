package com.springbootblog.springbootrestapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@SpringBootApplication
@Configuration
public class SpringBootRestApiApplication {

	@Bean
	public  ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiApplication.class, args);
	}



}
