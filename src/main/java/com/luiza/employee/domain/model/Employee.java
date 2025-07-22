package com.luiza.employee.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank
    private String department;
}
