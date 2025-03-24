package com.assignment.practice.colet.task.controller;

import com.assignment.practice.colet.task.DTO.DetailedEmployeeDTO;
import com.assignment.practice.colet.task.DTO.EmployeeDTO;
import com.assignment.practice.colet.task.entity.Employee;
import com.assignment.practice.colet.task.exceptions.NotFoundException;
import com.assignment.practice.colet.task.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/test")
    public String sayHello(){
        return "Hello User! ";
    }

    //Created this just to create some Employee Data
    @PostMapping("/employee")
    public EmployeeDTO createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);

    }

    //Created this get the all Employee Data
    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();

    }


    //Task:1
    //All employees with filters
    @GetMapping("/employees/filters")
    public List<EmployeeDTO> getAllEmployeesWithFilters(
            @RequestParam(required = false) LocalDate reviewDate,
            @RequestParam (required = false) String score,
            @RequestParam (required = false) List<Long> departmentIds,
            @RequestParam (required = false) List<String> projectNames
            ){
        return employeeService.getAllEmployeesWithFilters(reviewDate,score,departmentIds,projectNames);
    }



    //Task:2
    //Get detailed employee information
    @GetMapping("/employees/{id}")
    public DetailedEmployeeDTO getDetailedEmployeeDetailsById(@PathVariable Long id) throws NotFoundException {
        return employeeService.getDetailedEmployeeDetailsById(id);
    }
}
