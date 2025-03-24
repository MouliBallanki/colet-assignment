package com.assignment.practice.colet.task.service.employee;

import com.assignment.practice.colet.task.DTO.DetailedEmployeeDTO;
import com.assignment.practice.colet.task.DTO.EmployeeDTO;
import com.assignment.practice.colet.task.entity.Employee;
import com.assignment.practice.colet.task.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(Employee employee);

    List<EmployeeDTO> getAllEmployees();

    List<EmployeeDTO> getAllEmployeesWithFilters(LocalDate reviewDate, String score, List<Long> departmentIds, List<String> projectNames);

    DetailedEmployeeDTO getDetailedEmployeeDetailsById(Long id) throws NotFoundException;
}
