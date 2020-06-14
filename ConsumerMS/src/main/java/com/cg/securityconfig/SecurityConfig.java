package com.cg.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
////				.antMatchers("/consumerCtrl/search/**").hasRole("USER")	
//				.antMatchers("/consumerCtrl/createBooking").hasAnyRole("USER")										
//				.antMatchers("/consumerCtrl/search/**").hasAnyRole("USER")
//				.antMatchers("/").permitAll()
//				.and().formLogin();
		
		 http.httpBasic().and().authorizeRequests()
//		.antMatchers("/consumerCtrl/search/**").hasRole("USER")	
		.antMatchers("/consumerCtrl/createBooking").hasAnyRole("USER")										
		.antMatchers("/consumerCtrl/search/**").hasAnyRole("USER")
		.antMatchers("/").permitAll()
		   .and().csrf().disable().formLogin();
		 
	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	<dependency>
//	<groupId>org.springframework.cloud</groupId>
//	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
//</dependency>

}
