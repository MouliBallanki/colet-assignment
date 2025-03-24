package com.assignment.practice.colet.task.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate dateOfJoining;
    private Double salary;
    private Long departmentId;
    private Long managerId;
}
