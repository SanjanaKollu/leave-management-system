package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Authentication",description="Authentication APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager am;
	private final JwtService js;
	
	public AuthController(AuthenticationManager am,JwtService js)
	{
		this.am=am;
		this.js=js;
	}
	
	@Operation(summary="Login User",description="Authenticates user and returns JWT token")
	@ApiResponse(responseCode="200",description="Login Successfull")
	@ApiResponse(responseCode="401",description="Invalid Credentials")
	@PostMapping("/login")
	public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest req)
	{
		Authentication auth=am.authenticate(
				new UsernamePasswordAuthenticationToken(req.getEmail(),
						req.getPassword()));
		
		UserDetails ud=(UserDetails) auth.getPrincipal();
		String token=js.generateToken(ud);
		return ResponseEntity.ok(new LoginResponse(token));
	}
}
