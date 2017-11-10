package com.ciber.springBoot.HolaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.web.client.RestTemplate;

//utilizamos el exclude para quitar el distpatcherServlet por defecto en /* y coge el nuestro /s/*
@SpringBootApplication(exclude = { DispatcherServletAutoConfiguration.class})
public class HolaSpringBootApplication {
	
	//ACCESO A CUALQUIER API
	public static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		 //SpringApplication.run(new Class[] { HolaSpringBootApplication.class, MyWebApplicationInitializer.class }, args);
		SpringApplication.run(HolaSpringBootApplication.class, args);
	}
	
	
	
}
