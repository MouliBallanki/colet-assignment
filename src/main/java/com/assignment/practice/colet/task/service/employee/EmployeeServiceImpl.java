package com.assignment.practice.colet.task.service.employee;

import com.assignment.practice.colet.task.DTO.*;
import com.assignment.practice.colet.task.entity.Department;
import com.assignment.practice.colet.task.entity.Employee;
import com.assignment.practice.colet.task.entity.PerformanceReview;
import com.assignment.practice.colet.task.exceptions.NotFoundException;
import com.assignment.practice.colet.task.repository.DepartmentRepository;
import com.assignment.practice.colet.task.repository.EmployeeRepository;
import com.assignment.practice.colet.task.repository.PerformanceReviewRepository;
import com.assignment.practice.colet.task.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDTO saveEmployee(Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department existingDepartment = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(existingDepartment);
        }

        if (employee.getManager() != null && employee.getManager().getId() != null) {
            Employee existingManager = employeeRepository.findById(employee.getManager().getId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            employee.setManager(existingManager);
        }

        // Link existing PRs if they are already available
        if (employee.getPerformanceReviews() != null && !employee.getPerformanceReviews().isEmpty()) {
            List<PerformanceReview> attachedReviews = employee.getPerformanceReviews().stream()
                    .map(review -> {
                        if (review.getId() != null) {
                            return performanceReviewRepository.findById(review.getId())
                                    .orElseThrow(() -> new RuntimeException("Performance Review not found"));
                        } else {
                            review.setEmployee(employee);
                            return review;
                        }
                    })
                    .collect(Collectors.toList());

            employee.setPerformanceReviews(attachedReviews);
        }

        Employee savedEmployee = employeeRepository.save(employee);
        LOGGER.info("saving employee details ");
        return convertToDTO(savedEmployee);

    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        LOGGER.info("getting All Employee details ");
        return employees.stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<EmployeeDTO> getAllEmployeesWithFilters(LocalDate reviewDate, String score, List<Long> departmentIds, List<String> projectNames) {
        List<Employee> employees = employeeRepository.findEmployeesWithFilters(reviewDate, score, departmentIds, projectNames);
        return employees.stream().map(this::convertToDTO).toList();
    }

    @Override
    public DetailedEmployeeDTO getDetailedEmployeeDetailsById(Long id) throws NotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            LOGGER.error("Failed to get the employee for the given id = {}",id);
            throw new NotFoundException("Employee Not found ");
        }

        LOGGER.info("Getting detailed employee details ");

        return convertToDetailedEmployeeDTO(employee.get());

    }


    private DetailedEmployeeDTO convertToDetailedEmployeeDTO(Employee employee) {
        DetailedEmployeeDTO dto = new DetailedEmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setSalary(employee.getSalary());

        if (employee.getDepartment() != null) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(employee.getDepartment().getId());
            departmentDTO.setName(employee.getDepartment().getName());
            departmentDTO.setBudget(employee.getDepartment().getBudget());

            dto.setDepartmentDTO(departmentDTO);

            if (!projectRepository.findByDepartmentId(employee.getDepartment().getId()).isEmpty()) {

                List<ProjectDTO> projectDTOS = projectRepository.findByDepartmentId(employee.getDepartment().getId())
                        .stream()
                        .map(project -> new ProjectDTO(project.getId(), project.getName(), project.getStartDate(), project.getEndDate()))
                        .toList();
                dto.setProjectDTOs(projectDTOS);
            }

        }
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getId());
        }


        if (employee.getPerformanceReviews() != null) {
            List<PerformanceReviewDTO> reviewDTOs = employee.getPerformanceReviews().stream()
                    .sorted(Comparator.comparing(PerformanceReview::getReviewDate)) // Sort by reviewDate DESC
                    .limit(3) // Get only the latest 3
                    .map(review -> {
                        PerformanceReviewDTO reviewDTO = new PerformanceReviewDTO();
                        reviewDTO.setId(review.getId());
                        reviewDTO.setReview(review.getReviewComments());
                        reviewDTO.setScore(review.getScore());
                        reviewDTO.setReviewDate(review.getReviewDate());
                        return reviewDTO;
                    })
                    .collect(Collectors.toList());

            dto.setPerformanceReviews(reviewDTOs);
        }

        return dto;
    }


    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setSalary(employee.getSalary());

        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
        }
        if (employee.getManager() != null) {
            dto.setManagerId(employee.getManager().getId());
        }

        return dto;
    }
}
