package com.luiza.employee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class EmployeeResponse {
    private UUID id;
    private String name;
    private String email;
    private String department;
}