package com.luiza.employee.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    @NotBlank(message = "Name must not be blank")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Departament must not be blank")
    private String department;
}
