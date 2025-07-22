package com.luiza.employee.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeJpaEntity, UUID> {
}