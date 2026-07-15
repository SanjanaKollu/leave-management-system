package com.example.demo.dto;

import com.example.demo.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

public class EmployeeResponseDTO {
	
    @Schema(description = "Employee ID",example="1")
	private Long id;
    
	@Schema(description = "Employee Name",example = "Sanjana Reddy")
    private String name;
	
    @Schema(description = "Employee Mail",example = "sanjana@gmail.com")
    private String email;
    
    @Schema(description = "Department",example = "IT")
    private String department;
    
    @Schema(description = "Job Designation",example = "Software Engineer")
    private String designation;
    
    @Schema(description = "Available Leave Balance",example = "20")
    private Integer leaveBalance;
    
    @Schema(description = "Employee Role",example = "EMPLOYEE")
    private Role role;
    
    public EmployeeResponseDTO() {
    	
    }
  
	public EmployeeResponseDTO(Long id, String name, String email, String department, String designation,
			Integer leaveBalance, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
		this.designation = designation;
		this.leaveBalance = leaveBalance;
		this.role = role;
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

	public Integer getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Integer leaveBalance) {
		this.leaveBalance = leaveBalance;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    
}
