package com.spring.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
public class SpringSecurityOauth2Example1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOauth2Example1Application.class, args);
	}

}
