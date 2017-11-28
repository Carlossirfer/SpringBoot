package com.ciber.springBoot.HolaSpringBoot.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ciber
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.ciber.springBoot.HolaSpringBoot"})
public class AppConfig {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates");
        resolver.setSuffix(".html");
        return resolver;
    }
    
	
	 @Bean
	    public ServletRegistrationBean bbdd() {
	        DispatcherServlet dispatcherServlet = new DispatcherServlet();   
	        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	        applicationContext.register(AppConfig.class);
	        dispatcherServlet.setApplicationContext(applicationContext);
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/bbdd/*");
	        servletRegistrationBean.setName("bbdd");
	        return servletRegistrationBean;
	    }
	 
	 

	 @Bean
	    public ServletRegistrationBean rest() {
	        DispatcherServlet dispatcherServlet = new DispatcherServlet();   
	        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	        applicationContext.register(AppConfig.class);
	        dispatcherServlet.setApplicationContext(applicationContext);
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/rest/*");
	        servletRegistrationBean.setName("rest");
	        return servletRegistrationBean;
	    }
	 
	 @Bean
	    public ServletRegistrationBean normal() {
	        DispatcherServlet dispatcherServlet = new DispatcherServlet();   
	        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
	        applicationContext.register(AppConfig.class);
	        dispatcherServlet.setApplicationContext(applicationContext);
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/*");
	        servletRegistrationBean.setName("default");
	        return servletRegistrationBean;
	    }
	 
   
 
}
