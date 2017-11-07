/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author ciber
 *
 */
@Configuration
public class MyWebApplicationInitializer implements ServletContextInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("-------------------------SERVLET-----------------------------");
	    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.setConfigLocation("com.ciber.springBoot.HolaSpringBoot.config.AppConfig");

        ServletRegistration.Dynamic registration = servletContext.addServlet("springmvc", new DispatcherServlet(appContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
	}

}
