package com.ltim.product.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

	private final JWTFilter jwtFilter;
	
	 @Autowired 
	    public SecurityConfig(JWTFilter jwtFilter) {
	        this.jwtFilter = jwtFilter;
	    }
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/auth/login").permitAll()  // Allow login
	                .requestMatchers("/actuator/**").permitAll()
	                .requestMatchers(
	                    "/v3/api-docs/**", 
	                    "/swagger-ui/**", 
	                    "/swagger-ui.html"
	                ).permitAll()  //  Allow Swagger UI
	                .anyRequest().authenticated()
	            )
	            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(userDetailsService);
	        return new ProviderManager(provider);
	    }
	}


