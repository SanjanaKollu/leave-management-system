package com.example.demo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.entity.LeaveStatus;

public interface LeaveRepository extends JpaRepository<LeaveRequest,Long> {
	List<LeaveRequest> findByEmployeeId(Long employeeId);
	List<LeaveRequest> findByStatus(LeaveStatus status);
	long countByStatus(LeaveStatus status);
}
