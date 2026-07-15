package com.example.demo.mapper;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Employee;

public class EmployeeMapper {

	public static Employee requestDTOToEntity(EmployeeRequestDTO dto) {

	    Employee e = new Employee();

	    e.setName(dto.getName());
	    e.setEmail(dto.getEmail());
	    e.setPassword(dto.getPassword());
	    e.setDepartment(dto.getDepartment());
	    e.setDesignation(dto.getDesignation());

	 return e;
	}
	
	public static EmployeeResponseDTO entityToResponseDTO(Employee e) {

	    EmployeeResponseDTO dto = new EmployeeResponseDTO();

	    dto.setId(e.getId());
	    dto.setName(e.getName());
	    dto.setEmail(e.getEmail());
	    dto.setDepartment(e.getDepartment());
	    dto.setDesignation(e.getDesignation());
	    dto.setLeaveBalance(e.getLeaveBalance());
	    dto.setRole(e.getRole());

	    return dto;
	}
}
