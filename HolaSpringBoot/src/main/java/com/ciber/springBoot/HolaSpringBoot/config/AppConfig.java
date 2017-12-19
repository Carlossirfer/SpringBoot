package com.ciber.springBoot.HolaSpringBoot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ciber
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ciber.springBoot.HolaSpringBoot" })
public class AppConfig {

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/templates");
		resolver.setSuffix(".html");
		return resolver;
	}

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistrationBbdd() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/s/*");
        registration.setName("s");
        return registration;
    }
    
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistrationRest() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/rest/*");
//        registration.setName("rest");
//        return registration;
//    }
//    
//    
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistrationDefault() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/*");
//        registration.setName("default");
//        return registration;
//    }


}
