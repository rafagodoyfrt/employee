package com.luiza.employee.domain.repository;

import com.luiza.employee.adapter.out.persistence.EmployeeJpaEntity;
import com.luiza.employee.domain.model.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository {

    EmployeeJpaEntity save(Employee employee);

    List<Employee> findAll();

    void deleteById(UUID id);

    Optional<Employee> findById(UUID id);
}