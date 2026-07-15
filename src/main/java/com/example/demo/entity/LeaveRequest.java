package com.example.demo.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Enumerated(EnumType.STRING)
	    private LeaveType leaveType;

	    private LocalDate fromDate;

	    private LocalDate toDate;

	    @Column(length = 500)
	    private String reason;

	    private Integer days;

	    @Enumerated(EnumType.STRING)
	    private LeaveStatus status;
	    
	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private Employee employee;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}		
}
