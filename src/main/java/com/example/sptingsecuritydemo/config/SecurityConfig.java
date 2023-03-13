package com.example.sptingsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.sptingsecuritydemo.model.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.builder()
				.username("user")
				.password(passwordEncoder().encode("userPass"))
				.roles(Role.USER.name())
				.build());
		manager.createUser(
				User.builder()
				.username("admin")
				.password(passwordEncoder().encode("adminPass"))
				.roles(Role.ADMIN.name()).build());
		return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf()
		.disable()
		.authorizeHttpRequests()
		.requestMatchers("/").permitAll()
		.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
		.requestMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name())
		.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		return http.build();
		
	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
}
