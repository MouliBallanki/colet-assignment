package com.assignment.practice.colet.task.error;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private HttpStatus status;
    private String message;
}
