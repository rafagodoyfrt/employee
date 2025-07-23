package com.luiza.employee.adapter.out.persistence;

import com.luiza.employee.domain.model.Employee;
import com.luiza.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJpaRepository jpaRepository;

    public EmployeeRepositoryImpl(EmployeeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public EmployeeJpaEntity save(Employee employee) {
        EmployeeJpaEntity entity = new EmployeeJpaEntity(
                UUID.randomUUID(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment()
        );
        return jpaRepository.save(entity);
    }

    @Override
    public List<Employee> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> new Employee(
                        entity.getName(),
                        entity.getEmail(),
                        entity.getDepartment()
                ))
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(entity -> new Employee(
                        entity.getName(),
                        entity.getEmail(),
                        entity.getDepartment()
                ));
    }
}