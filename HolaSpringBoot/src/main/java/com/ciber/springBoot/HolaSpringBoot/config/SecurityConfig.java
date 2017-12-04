package com.ciber.springBoot.HolaSpringBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ciber.springBoot.HolaSpringBoot.filters.JwtFilter;
import com.ciber.springBoot.HolaSpringBoot.filters.LoginFilter;
import com.ciber.springBoot.HolaSpringBoot.security.MongoDBAuthenticationProvider;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MongoDBAuthenticationProvider authenticationProvider;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http
		.authorizeRequests()
		.antMatchers("/*").permitAll()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers("/bbdd/mongo").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/rest/api/posts").hasAnyAuthority("ROLE_USER")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.permitAll()
			.and()
//			 // We filter the api/login requests
//	        .addFilterBefore(new LoginFilter("/logueo", authenticationManager()),
//	                UsernamePasswordAuthenticationFilter.class)
//	        // And filter other requests to check the presence of JWT in header
//	        .addFilterBefore(new JwtFilter(),
//	                UsernamePasswordAuthenticationFilter.class)
		.logout()
			.logoutSuccessUrl("/login")                                           
			.and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
			.csrf().disable();
    	
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    
    
    
    
}
