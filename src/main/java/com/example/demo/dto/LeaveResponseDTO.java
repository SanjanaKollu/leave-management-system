package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.LeaveStatus;
import com.example.demo.entity.LeaveType;

import io.swagger.v3.oas.annotations.media.Schema;

public class LeaveResponseDTO {
        
	@Schema(description = "Employee ID", example = "1")
	  private Long id;
	  
	  @Schema(description = "Employee Name", example = "Sanjana Reddy")
	  private String name; 
	  
	  @Schema(description="Type of leave",example="CASUAL")
	  private LeaveType leaveType;
	  
	  @Schema(description = "Leave start date",
			    example = "2026-07-20")
	  private LocalDate fromDate;
	  
	  @Schema(description = "Leave end date",
			  example = "2026-07-22")
	  private LocalDate toDate;
	  
	  @Schema(description="No of days",example="5")
	  private Integer days;
	  
	  @Schema(description="Status of the leave",
			  example="APPROVED")
	  private LeaveStatus status;
	  
	  @Schema(description = "Reason for leave",
			    example = "Medical consultation")
	  private String reason;
	  
	  public LeaveResponseDTO() {
		  
	  }

	  public Long getId() {
		  return id;
	  }

	  public void setId(Long id) {
		  this.id = id;
	  }

	  public String getName()
	  {
		  return name;
	  }
	  
	  public void setName(String name)
	  {
		  this.name=name;
	  }
	  public LeaveType getLeaveType() {
		  return leaveType;
	  }

	  public void setLeaveType(LeaveType leaveType) {
		  this.leaveType = leaveType;
	  }

	  public LocalDate getFromDate() {
		  return fromDate;
	  }

	  public void setFromDate(LocalDate fromDate) {
		  this.fromDate = fromDate;
	  }

	  public LocalDate getToDate() {
		  return toDate;
	  }

	  public void setToDate(LocalDate toDate) {
		  this.toDate = toDate;
	  }

	  public Integer getDays() {
		  return days;
	  }

	  public void setDays(Integer days) {
		  this.days = days;
	  }

	  public LeaveStatus getStatus() {
		  return status;
	  }

	  public void setStatus(LeaveStatus status) {
		  this.status = status;
	  }

	  public String getReason() {
		  return reason;
	  }

	  public void setReason(String reason) {
		  this.reason = reason;
	  }

	  public LeaveResponseDTO(Long id, String name, LeaveType leaveType, LocalDate fromDate, LocalDate toDate, Integer days,
			LeaveStatus status, String reason) {
		super();
		this.id = id;
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.days = days;
		this.status = status;
		this.reason = reason;
	  }
	  
	  

}
