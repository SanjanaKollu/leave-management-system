package com.example.demo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.service.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
@Tag(name = "Leave Management", description = "Leave Request APIs")
public class LeaveController{
	
	  private final LeaveService ls;
	  public LeaveController(LeaveService ls)
	   {
		   this.ls=ls;
	   }
	  
	  @Operation(summary="Apply for leave",
		description="Allows an employee to submit a leave request.")
	  @ApiResponses({
		    @ApiResponse(responseCode = "201", description = "Leave request submitted successfully"),
		    @ApiResponse(responseCode = "404", description = "Employee not found"),
		    @ApiResponse(responseCode = "400", description = "Invalid leave request")
		})
	  @PostMapping("/{employeeId}/leaves")
	  public ResponseEntity<LeaveResponseDTO> applyLeave( @PathVariable Long employeeId,
		        @Valid @RequestBody LeaveRequestDTO dto)
	  {
		  LeaveResponseDTO res=ls.applyLeave(employeeId, dto);
		  return new ResponseEntity<>(res,HttpStatus.CREATED);	
	  }
	  
	  @Operation(summary="Get Employee Leave History",description="Retrieves all"
	  		+ "leave requests submitted by a specific Employee")
	  @ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Leave history retrieved successfully"),
		    @ApiResponse(responseCode = "404", description = "Employee not found")
		})
	  @GetMapping("/{employeeId}/leaves")
	  public ResponseEntity<List<LeaveResponseDTO>> getLeaveHistory(
			  @PathVariable Long employeeId)
	  {
		   List<LeaveResponseDTO> l=ls.getLeavesByEmployee(employeeId);
		   return ResponseEntity.ok(l);
	  }
}
