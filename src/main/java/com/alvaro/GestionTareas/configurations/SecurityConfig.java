package com.alvaro.GestionTareas.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable()
		.authorizeRequests()
		.anyRequest().permitAll()
		.and()
		.httpBasic();
		
		
		/*
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/employee").hasAuthority("ADMIN") 
		.antMatchers(HttpMethod.POST, "/employee").hasAuthority("USER") 
		.antMatchers(HttpMethod.GET, "/task").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET, "/task/email/*").hasAuthority("USER")
		.antMatchers(HttpMethod.POST, "/task").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/task/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.PUT,"/task").hasAuthority("USER")
		.and()
		.httpBasic();
		*/
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		String pwdEncode = new BCryptPasswordEncoder().encode("1234");
	
		authentication.inMemoryAuthentication()
		.withUser("admin").password(pwdEncode).authorities("ADMIN")
		.and()
		.withUser("user").password(pwdEncode).authorities("USER");
	}
}
