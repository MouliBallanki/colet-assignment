package com.assignment.practice.colet.task.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "department name is required")
    private String name;
    private Double budget;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<Project> projects;

}
