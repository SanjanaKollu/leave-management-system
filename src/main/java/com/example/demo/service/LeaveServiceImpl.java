package com.example.demo.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.LeaveStatus;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.LeaveNotFoundException;
import com.example.demo.mapper.LeaveMapper;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.repo.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService  {

	private final LeaveRepository lr;
	private final EmployeeRepository er;
	
	public LeaveServiceImpl(LeaveRepository lr,EmployeeRepository er)
	{
		this.lr=lr;
		this.er=er;
	}
	
	@Override
	public LeaveResponseDTO applyLeave(Long employeeId, LeaveRequestDTO dto) {
		  Employee emp=er.findById(employeeId)
				  .orElseThrow(()->new EmployeeNotFoundException( "Employee not found with id : " + employeeId));
		  LeaveRequest l=LeaveMapper.requestDTOToEntity(dto);
		  
		 if(dto.getFromDate().isAfter(dto.getToDate())) {
			 throw new IllegalArgumentException("from date cannot be after to date");
		 }
		 
		long days=ChronoUnit.DAYS.between
				 (dto.getFromDate(),dto.getToDate())+1;
		l.setDays((int) days);
		
		if (days > emp.getLeaveBalance()) {
            throw new IllegalArgumentException(
		            "Insufficient leave balance");
		}
		l.setStatus(LeaveStatus.PENDING);
		l.setEmployee(emp);
		
		LeaveRequest savedLeave = lr.save(l);
		return LeaveMapper.entityToResponseDTO(savedLeave);
	}

	@Override
	public List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId) {
		  Employee e=er.findById(employeeId)
				  .orElseThrow(()->new EmployeeNotFoundException
						  ("Employee not found with id:"+employeeId));
		  List<LeaveRequest> leaves = lr.findByEmployeeId(employeeId);
		  List<LeaveResponseDTO>response=new ArrayList<>();
		  for(LeaveRequest l:leaves)
		  {
			  response.add(LeaveMapper.entityToResponseDTO(l));
		  }
		return response;
	}

	@Override
	public LeaveResponseDTO approveLeave(Long leaveId) {
	    LeaveRequest leave = lr.findById(leaveId)
	            .orElseThrow(() ->new LeaveNotFoundException("Leave not found with id : " + leaveId));

	    if (leave.getStatus() != LeaveStatus.PENDING) {
	        throw new IllegalStateException(
	                "Only pending leave can be approved");
	    }

	    Employee employee = leave.getEmployee();
	    if (employee.getLeaveBalance() < leave.getDays()) {
	        throw new IllegalArgumentException(
	                "Insufficient leave balance");
	    }
	    employee.setLeaveBalance(employee.getLeaveBalance() - leave.getDays());
	    leave.setStatus(LeaveStatus.APPROVED);
	    er.save(employee);
	    LeaveRequest savedLeave = lr.save(leave);
	    return LeaveMapper.entityToResponseDTO(savedLeave);
	}

	@Override
	public LeaveResponseDTO rejectLeave(Long leaveId) {
		 LeaveRequest l=lr.findById(leaveId)
				 .orElseThrow(()->new LeaveNotFoundException("Leave not found with"
				 		+ " id : " + leaveId));
			if(l.getStatus()!=LeaveStatus.PENDING)
			{
				throw new IllegalStateException("Only pending leaves can be rejected");
			}
	    l.setStatus(LeaveStatus.REJECTED);
	    LeaveRequest savedLeave=lr.save(l);
	    return LeaveMapper.entityToResponseDTO(savedLeave);
	}

	@Override
	public List<LeaveResponseDTO> getAllLeaves() {
		List<LeaveRequest> l=lr.findAll();
		List<LeaveResponseDTO> res=new ArrayList<>();
		for(LeaveRequest lr:l)
		{
			res.add(LeaveMapper.entityToResponseDTO(lr));
		}
		return res;
	}

	@Override
	public List<LeaveResponseDTO> getPendingLeaves() {
		List<LeaveRequest> leave=lr.findByStatus(LeaveStatus.PENDING);
		List<LeaveResponseDTO> res=new ArrayList<>();
		for(LeaveRequest l:leave)
		{
			res.add(LeaveMapper.entityToResponseDTO(l));
		}
		return res;
	}

	@Override
	public List<LeaveResponseDTO> getApprovedLeaves() {
		List<LeaveRequest> leave=lr.findByStatus(LeaveStatus.APPROVED);
		List<LeaveResponseDTO> res=new ArrayList<>();
		for(LeaveRequest l:leave)
		{
			res.add(LeaveMapper.entityToResponseDTO(l));
		}
		return res;
	}

	@Override
	public List<LeaveResponseDTO> getRejectedLeaves() {
		List<LeaveRequest> leave=lr.findByStatus(LeaveStatus.REJECTED);
		List<LeaveResponseDTO> res=new ArrayList<>();
		for(LeaveRequest l:leave)
		{
			res.add(LeaveMapper.entityToResponseDTO(l));
		}
		return res;
	}

}

