package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequestDTO  {
     
	    private Long id;   
	    @NotBlank(message = "Name is required")
	    private String name;

	    @Email(message = "Invalid email")
	    @Schema(example="John Doe")
	    @NotBlank(message = "Email is required")
	    private String email;

	    @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters")
	    @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
	    private String password;

	    @NotBlank(message = "Department is required")
	    private String department;
	    
	    @Schema(example="Developer")
	    @NotBlank(message = "Designation is required")
	    private String designation;


	    public EmployeeRequestDTO() {
	    	
	    }
	    
	    public EmployeeRequestDTO(Long id,String name,String email,String password,String department,String designation) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.department = department;
			this.designation = designation;
		}



		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}	    
}
