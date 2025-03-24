package com.assignment.practice.colet.task.DTO;

import com.assignment.practice.colet.task.entity.Department;
import com.assignment.practice.colet.task.entity.PerformanceReview;
import com.assignment.practice.colet.task.entity.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DetailedEmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private Double salary;
    private Long managerId;

    private DepartmentDTO departmentDTO;

    private List<ProjectDTO> projectDTOs;

    private List<PerformanceReviewDTO> performanceReviews;
}
