package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;

@Service
public interface LeaveService 
{
	LeaveResponseDTO applyLeave(Long employeeId,LeaveRequestDTO dto);
	List<LeaveResponseDTO> getLeavesByEmployee(Long LeaveId);
	LeaveResponseDTO approveLeave(Long leaveId);
	LeaveResponseDTO rejectLeave(Long leaveId);
	List<LeaveResponseDTO> getAllLeaves();
	List<LeaveResponseDTO> getPendingLeaves();
	List<LeaveResponseDTO> getApprovedLeaves();
	List<LeaveResponseDTO> getRejectedLeaves();
}
