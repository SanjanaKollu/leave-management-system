package com.example.demo.controller;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.service.LeaveService;

@Tag(name="Leave Administration",description="APIs for managing leave requests")
@RestController
@RequestMapping("/leaves")
public class LeaveAdminController {
	 private final LeaveService ls;
	 public LeaveAdminController(LeaveService ls)
	    {
		     this.ls=ls;
	    }
	 
	    
	    @GetMapping
	    @Operation(summary="Get All Leave Requests",description=""
	    		+ "Retrieves all leave requests submitted by employees.")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Leave requests retrieved successfully"),
	        @ApiResponse(responseCode = "401", description = "Unauthorized"),
	        @ApiResponse(responseCode = "403", description = "Access denied")
	    })
	    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaves() {
	        return ResponseEntity.ok(ls.getAllLeaves());
	    }

	    @Operation(summary="Approve Leave Request",description="Approves a pending leave.")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Leave request approved successfully"),
	        @ApiResponse(responseCode = "404", description = "Leave request not found"),
	        @ApiResponse(responseCode = "401", description = "Unauthorized"),
	        @ApiResponse(responseCode = "403", description = "Access denied")
	    })
	    @PutMapping("/{leaveId}/approve")
	    public ResponseEntity<LeaveResponseDTO> approveLeave(@PathVariable Long leaveId) {
	        return ResponseEntity.ok(ls.approveLeave(leaveId));
	    }

	    @Operation(summary="Reject Leave Request",description="Rejects a pending leave request.")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Leave request approved successfully"),
	        @ApiResponse(responseCode = "404", description = "Leave request not found"),
	        @ApiResponse(responseCode = "401", description = "Unauthorized"),
	        @ApiResponse(responseCode = "403", description = "Access denied")
	    })
	    @PutMapping("/{leaveId}/reject")
	    public ResponseEntity<LeaveResponseDTO> rejectLeave(@PathVariable Long leaveId) {
	        return ResponseEntity.ok(ls.rejectLeave(leaveId));
	    }

	    @Operation(summary="Get Pending Leave Requests",description=""
	    		+ "Retrieves all leave requsts that are awaiting for approval")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Pending leave requests retrieved successfully"),
	        @ApiResponse(responseCode = "401", description = "Unauthorized"),
	        @ApiResponse(responseCode = "403", description = "Access denied")
	    })
	    @GetMapping("/pending")
	    public ResponseEntity<List<LeaveResponseDTO>> getPendingLeaves() {
	        return ResponseEntity.ok(ls.getPendingLeaves());
	    }

	    @Operation(summary="Get Approved Leave Requests",
	    		description="Retrieves all approved leave requests.")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Approved leave requests retrieved successfully"),
	        @ApiResponse(responseCode = "401", description = "Unauthorized"),
	        @ApiResponse(responseCode = "403", description = "Access denied")
	    })
	    @GetMapping("/approved")
	    public ResponseEntity<List<LeaveResponseDTO>> getApprovedLeaves() {
	        return ResponseEntity.ok(ls.getApprovedLeaves());
	    }

	    @Operation(summary="Get Rejected Leave Requests",description="Retrieves all rejected leave requests")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Approved leave requests retrieved successfully"),
	        @ApiResponse(responseCode = "401", description = "Unauthorized"),
	        @ApiResponse(responseCode = "403", description = "Access denied")
	    })
	    @GetMapping("/rejected")
	    public ResponseEntity<List<LeaveResponseDTO>> getRejectedLeaves() {
	        return ResponseEntity.ok(ls.getRejectedLeaves());
	    }
}
