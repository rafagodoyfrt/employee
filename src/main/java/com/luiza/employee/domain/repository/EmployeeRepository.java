package com.luiza.employee.domain.repository;

import com.luiza.employee.domain.model.Employee;
import com.luiza.employee.domain.model.EmployeeResponse;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository {

    EmployeeResponse save (Employee employee);

    List<EmployeeResponse> findAll();

    void deleteById(UUID id);

    EmployeeResponse updateById(UUID id, Employee employee);
}
