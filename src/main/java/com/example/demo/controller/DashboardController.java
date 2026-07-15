package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.DashboardResponseDTO;
import com.example.demo.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Dashboard", description = "Dashboard APIs")
@RestController
public class DashboardController {
       
	private final DashboardService ds;    
	 public DashboardController(DashboardService ds)
	   {
		   this.ds=ds;
	   }
	
	 @Operation(summary = "Get Dashboard Statistics",description = "Retrieves dashboard statistics including employee count, leave requests, "
	 		+ "approved leaves, pending leaves, and rejected leaves.")
	 @ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Dashboard statistics retrieved successfully"),
		    @ApiResponse(responseCode = "401", description = "Unauthorized"),
		    @ApiResponse(responseCode = "403", description = "Access denied")
		})
	 @GetMapping("/dashboard")
	 public ResponseEntity<DashboardResponseDTO> getDashboard()
	 {
		 DashboardResponseDTO d=ds.getDashboard();
		 return ResponseEntity.ok(d);
	 }
}
