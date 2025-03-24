package com.assignment.practice.colet.task.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PerformanceReviewDTO {
    private Long id;
    private String review;
    private String score;
    private LocalDate reviewDate;
}
