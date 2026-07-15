package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	private final CustomUserDetailsService cs;
	private final PasswordEncoder pe;
	private final JwtAuthFilter jf;
	
	public SecurityConfig(CustomUserDetailsService cs,PasswordEncoder pe,JwtAuthFilter jf)
	{
		this.cs=cs;
		this.pe=pe;
		this.jf=jf;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		      provider.setUserDetailsService(cs);
		    provider.setPasswordEncoder(pe);
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		 return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf(csrf->csrf.disable())
		.sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

.exceptionHandling(ex -> ex.authenticationEntryPoint(
        (request, response, exception) ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
))
		.authorizeHttpRequests(auth -> auth
				  .requestMatchers(HttpMethod.POST, "/employees").permitAll()
				    .requestMatchers("/auth/login").permitAll()
				    .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html")
				    .permitAll()
				    // Admin only
				    .requestMatchers("/dashboard/**").hasRole("ADMIN")
				    
				    .requestMatchers("/leaves/**").hasRole("ADMIN")

				    // Authenticated users
				    .requestMatchers("/employees", "/employees/**").authenticated()

	            .anyRequest()
	            .authenticated())
		 .authenticationProvider(authenticationProvider())
		 .addFilterBefore(jf,
		            UsernamePasswordAuthenticationFilter.class);
	   return http.build();
	}
}
