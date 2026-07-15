package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
       
	   @Schema(description = "Employee Email", example = "sanjana@gmail.com")
	   private String email;
	   
	   @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
	   private String password;
	   
	   public LoginRequest() {
		   
	   }

	   public String getEmail() {
		   return email;
	   }

	   public void setEmail(String email) {
		   this.email = email;
	   }

	   public String getPassword() {
		   return password;
	   }

	   public void setPassword(String password) {
		   this.password = password;
	   }
	   	   
}
