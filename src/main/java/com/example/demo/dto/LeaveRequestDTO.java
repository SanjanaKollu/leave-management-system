package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.LeaveType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Leave request details")
public class LeaveRequestDTO 
{
	@NotNull(message = "Leave type is required")
	@Schema(description="Type of leave",example="CASUAL")
    private LeaveType leaveType;

    @NotNull(message = "From date is required")
    @FutureOrPresent(message = "From date cannot be in the past")
    @Schema(description = "Leave start date",
    	    example = "2026-07-20"
    	)
    private LocalDate fromDate;

    @Schema(description = "Leave end date",
    	    example = "2026-07-22"
    	)
    @NotNull(message = "To date is required")
    @FutureOrPresent(message = "To date cannot be in the past")
    private LocalDate toDate;

    @NotBlank(message = "Reason is required")
    @Schema(description = "Reason for leave",example="Medical consultation")
    @Size(min = 5, max = 500, message = "Reason must be between 5 and 500 characters")
    private String reason;

    public LeaveRequestDTO() {
    	
    }
    
	public LeaveRequestDTO(@NotNull(message = "Leave type is required") LeaveType leaveType,LocalDate fromDate,LocalDate toDate,String reason) {
		super();
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.reason = reason;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
}
