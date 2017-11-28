//package com.ciber.springBoot.HolaSpringBoot.config;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
///**
// * @author ciber
// *
// */
//@Configuration
//public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
//	
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return null;
//	}
//
//	
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return null;
//	}
//
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[]{"/s/*"};
//	}
//
////	@Override
////	public void onStartup(ServletContext servletContext) throws ServletException {
////		System.out.println("------------------------SERVLET-----------------------------");
////	    AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
////	    appContext.register(AppConfig.class);
////	    appContext.setServletContext(servletContext);
////        appContext.setConfigLocation("com.ciber.springBoot.HolaSpringBoot.config.AppConfig");
////
////        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(appContext));
////        registration.setLoadOnStartup(1);
////        registration.addMapping("/s/*");
////	}
//
//}
