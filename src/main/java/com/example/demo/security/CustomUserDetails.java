package com.example.demo.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.entity.Employee;

public class CustomUserDetails implements UserDetails {

	private final Employee emp;
	
	public CustomUserDetails(Employee emp)
	{
		this.emp=emp;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	      return List.of(new SimpleGrantedAuthority("ROLE "+emp.getRole().name()));
	}

	@Override
	public String getPassword() {
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		return emp.getEmail();
	}

}
