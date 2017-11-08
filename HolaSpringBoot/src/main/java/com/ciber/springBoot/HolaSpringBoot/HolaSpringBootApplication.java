package com.ciber.springBoot.HolaSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.web.client.RestTemplate;

//utilizamos el exclude para quitar el distpatcherServlet por defecto en /* y coge el nuestro /s/*
@SpringBootApplication(exclude = { DispatcherServletAutoConfiguration.class})
public class HolaSpringBootApplication {

	//CONSTANTE CON LA URL DE LA API
	public static final String URL_API_BOOKS = "http://private-114e-booksapi.apiary-mock.com/books/";
	
	//CONSTANTE CON LA URL DE LA API PRUEBA
	public static final String URL_API_PRUEBA = "https://jsonplaceholder.typicode.com/posts/";
	
	//ACCESO A CUALQUIER API
	public static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		 //SpringApplication.run(new Class[] { HolaSpringBootApplication.class, MyWebApplicationInitializer.class }, args);
		SpringApplication.run(HolaSpringBootApplication.class, args);
	}
	
}
