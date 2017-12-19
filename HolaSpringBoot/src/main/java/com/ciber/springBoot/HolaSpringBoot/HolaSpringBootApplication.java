package com.ciber.springBoot.HolaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


//utilizamos el exclude para quitar el distpatcherServlet por defecto en /* y coge el nuestro /s/*
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DispatcherServletAutoConfiguration.class} )
public class HolaSpringBootApplication extends WebMvcAutoConfiguration {

	public static void main(String[] args) {
//		 SpringApplication.run(new Class[] { HolaSpringBootApplication.class, MyWebApplicationInitializer.class }, args);
		SpringApplication.run(HolaSpringBootApplication.class, args);
	}


	// ACCESO A APIREST
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
