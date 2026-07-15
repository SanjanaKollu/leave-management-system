package com.example.demo.security;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository er;

    public CustomUserDetailsService(EmployeeRepository er) {
        this.er = er;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Employee emp = er.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new User(
                emp.getEmail(),
                emp.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + emp.getRole()))
        );
    }
}