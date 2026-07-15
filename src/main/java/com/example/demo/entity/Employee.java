package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Employee 
{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
	    private String password;
	    private String department;
	    private String designation;
	    private Integer leaveBalance;

	    @OneToMany(mappedBy = "employee",
	            cascade = CascadeType.ALL,
	            orphanRemoval = true)
	   private List<LeaveRequest> leaves = new ArrayList<>();
	    
	    @Enumerated(EnumType.STRING)
	    private Role role;

	    public Employee() {

	    }
	    
	    public Employee(Long id, String name, String email, String password,
                String department, String designation,
                Integer leaveBalance, Role role) {

                        this.id = id;
                        this.name = name;
                        this.email = email;
                        this.password = password;
                        this.department = department;
                        this.designation = designation;
                        this.leaveBalance = leaveBalance;
                         this.role = role;
               }

		public long getId() {
			return id;
		}

		public void setId(long id) {
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
