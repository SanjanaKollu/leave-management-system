package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response containing JWT token")
public class LoginResponse {

	@Schema(
		    description = "JWT access token",
		    example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYW5qYW5hQGdtYWlsLmNvbSIsImlhdCI6MTcyMDYwMDAwMCwiZXhwIjoxNzIwNjAzNjAwfQ.xxxxxxxxxxxxxxxxxxxxxxxxx"
		)
	    private String token;
	    
	    public LoginResponse() {
	    	
	    }

		public LoginResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
		    
}
