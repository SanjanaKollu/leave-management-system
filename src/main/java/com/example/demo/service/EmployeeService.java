package com.example.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;

public interface EmployeeService 
{
	    EmployeeResponseDTO addEmployee(EmployeeRequestDTO employee);
	    List<EmployeeResponseDTO> getAllEmployees();
	    EmployeeResponseDTO getEmployeeById(Long employeeId);
	    EmployeeResponseDTO updateEmployee(Long employeeId, EmployeeRequestDTO employee);
	    void deleteEmployee(Long employeeId);
	    List<EmployeeResponseDTO> searchEmployees(String name);
	    Page<EmployeeResponseDTO> getEmployees(int page, int size);
	    Page<EmployeeResponseDTO> getEmployees(int page,int size,String sortBy);
	    List<EmployeeResponseDTO> filterByDepartment(String department);
	    EmployeeResponseDTO getLoggedInEmployee();
}
