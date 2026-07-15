package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dashboard statistics response")
public class DashboardResponseDTO {
      
	  @Schema(description = "Total number of Employees", example = "1")
	  private long totalEmployees;
	  
	  @Schema(description = "Total number of Leave Requests", example = "2")
	  private long totalLeaveRequests;
	  
	  @Schema(description = "Number of pending Leave requests", example = "1")
	  private long pendingLeaves;
	  
	  @Schema(description = "Number of approved leaves requests", example = "1")
	  private long approvedLeaves;
	  
	  @Schema(description = "Number of rejected leave requests", example = "1")
	  private long rejectedLeaves;
	  
	  public DashboardResponseDTO() {
		  
	  }

	  public DashboardResponseDTO(long totalEmployees, long totalLeaveRequests, long pendingLeaves, long approvedLeaves,
			long rejectedLeaves) {
		super();
		this.totalEmployees = totalEmployees;
		this.totalLeaveRequests = totalLeaveRequests;
		this.pendingLeaves = pendingLeaves;
		this.approvedLeaves = approvedLeaves;
		this.rejectedLeaves = rejectedLeaves;
	  }

	  public long getTotalEmployees() {
		  return totalEmployees;
	  }

	  public void setTotalEmployees(long totalEmployees) {
		  this.totalEmployees = totalEmployees;
	  }

	  public long getTotalLeaveRequests() {
		  return totalLeaveRequests;
	  }

	  public void setTotalLeaveRequests(long totalLeaveRequests) {
		  this.totalLeaveRequests = totalLeaveRequests;
	  }

	  public long getPendingLeaves() {
		  return pendingLeaves;
	  }

	  public void setPendingLeaves(long pendingLeaves) {
		  this.pendingLeaves = pendingLeaves;
	  }

	  public long getApprovedLeaves() {
		  return approvedLeaves;
	  }

	  public void setApprovedLeaves(long approvedLeaves) {
		  this.approvedLeaves = approvedLeaves;
	  }

	  public long getRejectedLeaves() {
		  return rejectedLeaves;
	  }

	  public void setRejectedLeaves(long rejectedLeaves) {
		  this.rejectedLeaves = rejectedLeaves;
	  }
	  
}
