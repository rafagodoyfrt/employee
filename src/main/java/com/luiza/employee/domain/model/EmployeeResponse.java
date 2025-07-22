package com.luiza.employee.domain.model;

import com.luiza.employee.adapter.out.persistence.EmployeeJpaEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String email;
    private String department;
}