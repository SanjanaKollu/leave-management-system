package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.DashboardResponseDTO;
import com.example.demo.entity.LeaveStatus;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.LeaveRepository;

@Service
public class DashboardServiceImpl implements DashboardService{

	 private final EmployeeRepository er;
	 private final LeaveRepository lr;
	 
	 public DashboardServiceImpl(EmployeeRepository er,
			 LeaveRepository lr)
	 {
		 this.er=er;
		 this.lr=lr;
	 }
	
	public DashboardResponseDTO getDashboard() {
		  long totalEmployees=er.count();
		  long totalLeaveRequests=lr.count();
		  long pendingLeaves=lr.countByStatus(LeaveStatus.PENDING);
		  long approvedLeaves=lr.countByStatus(LeaveStatus.APPROVED);
		  long rejectedLeaves=lr.countByStatus(LeaveStatus.REJECTED);
	  return new DashboardResponseDTO(totalEmployees,totalLeaveRequests,pendingLeaves, approvedLeaves, rejectedLeaves);
	}

}
