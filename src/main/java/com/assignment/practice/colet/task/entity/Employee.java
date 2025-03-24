package com.assignment.practice.colet.task.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "employee name is required ")
    private String name;

    @NotBlank(message = "employee mail is required")
    @Email
    @Column(unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    private LocalDate dateOfJoining;

    @NotNull(message = "Salary field is required")
    @Positive(message = "Salary must be greater than zero")
    private Double salary;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Employee manager;



    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Employee> employees;


    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<PerformanceReview> performanceReviews;

}
