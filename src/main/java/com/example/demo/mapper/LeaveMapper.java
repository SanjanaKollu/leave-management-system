package com.example.demo.mapper;

import com.example.demo.dto.LeaveRequestDTO;
import com.example.demo.dto.LeaveResponseDTO;
import com.example.demo.entity.LeaveRequest;

public class LeaveMapper {

	public static LeaveRequest requestDTOToEntity(LeaveRequestDTO dto) 
	{
		LeaveRequest l = new LeaveRequest();
	    l.setLeaveType(dto.getLeaveType());
	    l.setFromDate(dto.getFromDate());
	    l.setToDate(dto.getToDate());
	    l.setReason(dto.getReason());
	    return l;
	}
	
	public static LeaveResponseDTO entityToResponseDTO(LeaveRequest l) 
	{
	    LeaveResponseDTO dto = new LeaveResponseDTO();
	    dto.setId(l.getId());
	    dto.setName(l.getEmployee().getName());
	    dto.setLeaveType(l.getLeaveType());
	    dto.setFromDate(l.getFromDate());
	    dto.setToDate(l.getToDate());
	    dto.setDays(l.getDays());
	    dto.setStatus(l.getStatus());
	    dto.setReason(l.getReason());
	    return dto;
	}
}
