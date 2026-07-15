package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Role;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository er;
	private final PasswordEncoder pe; 
	
	public EmployeeServiceImpl(EmployeeRepository er,PasswordEncoder pe) {
        this.er = er;
        this.pe=pe;
    }
	  
	@Override
	public EmployeeResponseDTO addEmployee(EmployeeRequestDTO dto) {
		 if (er.findByEmail(dto.getEmail()).isPresent()) {
		        throw new EmailAlreadyExistsException("Email already exists");
		    }
		Employee e=EmployeeMapper.requestDTOToEntity(dto);
		   e.setPassword(pe.encode(dto.getPassword()));
		    e.setLeaveBalance(20);
		    e.setRole(Role.EMPLOYEE);
		Employee savedEmployee=er.save(e);
		    return EmployeeMapper.entityToResponseDTO(savedEmployee);
	}

	@Override
	public List<EmployeeResponseDTO> getAllEmployees() {
		List<Employee> e=er.findAll();
		List<EmployeeResponseDTO>response=new ArrayList<>();
	  for(Employee emp:e)
		{
		    response.add(EmployeeMapper.entityToResponseDTO(emp));    
		}
	  return response;
	}

	@Override
	public EmployeeResponseDTO  getEmployeeById(Long employeeId) {
		Employee e=er.findById(employeeId)
			.orElseThrow(()->new EmployeeNotFoundException(
	                "Employee not found with id : " + employeeId));
	   return EmployeeMapper.entityToResponseDTO(e);
	}

	@Override
	public EmployeeResponseDTO updateEmployee(Long employeeId, EmployeeRequestDTO dto) {
		  Employee employee = er.findById(employeeId)
		            .orElseThrow(() -> new EmployeeNotFoundException(
		                    "Employee not found with id : " + employeeId));

		  Employee existing = er.findByEmail(dto.getEmail()).orElse(null);

		  if (existing != null && existing.getId() != employeeId) {
		      throw new EmailAlreadyExistsException("Email already exists");
		  }
		    employee.setName(dto.getName());
		    employee.setEmail(dto.getEmail());
		    employee.setPassword(pe.encode(dto.getPassword()));
		    employee.setDepartment(dto.getDepartment());
		    employee.setDesignation(dto.getDesignation());
		 Employee updatedEmployee = er.save(employee);
		  return EmployeeMapper.entityToResponseDTO(updatedEmployee);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = er.findById(employeeId)
		        .orElseThrow(() -> new EmployeeNotFoundException(
	                    "Employee not found with id : " + employeeId));
		er.delete(employee);
	}

	@Override
	public List<EmployeeResponseDTO> searchEmployees(String name) {
		  List<Employee> emp=er.findByNameContainingIgnoreCase(name);
		List<EmployeeResponseDTO> res=new ArrayList<>();
		for(Employee e:emp)
		{
			res.add(EmployeeMapper.entityToResponseDTO(e));
		}
	 return res;
	}

	@Override
	public Page<EmployeeResponseDTO> getEmployees(int page, int size) 
	{
		 Pageable p=PageRequest.of(page, size);
		 Page<Employee> employees = er.findAll(p);
		    return employees.map(EmployeeMapper::entityToResponseDTO);
	}

	@Override
	public Page<EmployeeResponseDTO> getEmployees(int page, int size, String sortBy) 
	{
		  Pageable p=PageRequest.of(page, size, Sort.by(sortBy));
		  Page<Employee> employees = er.findAll(p);
		 return employees.map(EmployeeMapper::entityToResponseDTO);
	}

	@Override
	public List<EmployeeResponseDTO> filterByDepartment(String department) {
		 List<Employee> emp=er.findByDepartmentIgnoreCase(department);
		 List<EmployeeResponseDTO> res=new ArrayList<>();
		 for(Employee e:emp)
		 {
			 res.add(EmployeeMapper.entityToResponseDTO(e));
		 }
	  return res;
	}

	@Override
	public EmployeeResponseDTO getLoggedInEmployee() {
		String email=SecurityContextHolder.getContext()
				.getAuthentication().getName();
		Employee emp=er.findByEmail(email)
				.orElseThrow(()-> new EmployeeNotFoundException(
						"Employee not found"));
		return EmployeeMapper.entityToResponseDTO(emp);
	}

}
