package com.example.demo.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name="Employee Management",description="Employee CRUD APIs")
@RestController
@RequestMapping("/employees")
public class EmployeeController {
 
	private final EmployeeService es;

	public EmployeeController(EmployeeService es) {
	    this.es = es;
	}
	
	@Operation(summary="Add Employee")
	@ApiResponse(responseCode="201",description="Employee Created")
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> addEmployee(@Valid @RequestBody EmployeeRequestDTO e) {
	   EmployeeResponseDTO res=es.addEmployee(e);
	   return new ResponseEntity<>(res,HttpStatus.CREATED);
	}
	
	@Operation(summary = "Get All Employees")
	@ApiResponse(responseCode = "200", description = "Employees Retrieved")
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
		List<EmployeeResponseDTO> emp=es.getAllEmployees();
	    return ResponseEntity.ok(emp);
	}
	
	@Operation(summary = "Get Employee By ID")
	@ApiResponse(responseCode = "200", description = "Employee Found")
	@ApiResponse(responseCode = "404", description = "Employee Not Found")
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long employeeId) {
		EmployeeResponseDTO emp=es.getEmployeeById(employeeId);
	    return ResponseEntity.ok(emp);

	}
	
	@Operation(summary = "Update Employee")
	@ApiResponse(responseCode = "200", description = "Employee Updated")
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long employeeId,
	                               @Valid @RequestBody EmployeeRequestDTO dto) {
		EmployeeResponseDTO emp =
	            es.updateEmployee(employeeId, dto);
	    return ResponseEntity.ok(emp);
	}
	
	@Operation(summary = "Delete Employee")
	@ApiResponse(responseCode = "200", description = "Employee Deleted")
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
	    es.deleteEmployee(employeeId);
	    return ResponseEntity.ok("Employee deleted successfully");
	}
	
	@Operation(summary = "Search Employees by Name")
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeResponseDTO>>
	    searchEmployees(@RequestParam String name)
	    {
		     return ResponseEntity.ok(es.searchEmployees(name));
	    }
	
	@Operation(summary = "Get Employees with Pagination")
	@GetMapping("/page")
	public ResponseEntity<Page<EmployeeResponseDTO>> getEmployees(@RequestParam int page,@RequestParam int size,
	        @RequestParam String sort) {
	    return ResponseEntity.ok(
	            es.getEmployees(page, size, sort));
	}
	
	@Operation(summary = "Filter Employees by Department")
	@GetMapping("/filter")
	public ResponseEntity<List<EmployeeResponseDTO>> filterEmployees(
	        @RequestParam String department) {

	    return ResponseEntity.ok(
	            es.filterByDepartment(department));
	}
	
	@GetMapping("/me")
	public ResponseEntity<EmployeeResponseDTO> getLoggedInEmployee()
	{
		 return ResponseEntity.ok(es.getLoggedInEmployee());
	}
}
