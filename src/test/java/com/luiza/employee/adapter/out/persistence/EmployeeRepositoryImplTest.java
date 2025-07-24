package com.luiza.employee.adapter.out.persistence;

import com.luiza.employee.domain.model.Employee;
import com.luiza.employee.domain.model.EmployeeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeRepositoryImplTest {

    private EmployeeJpaRepository jpaRepository;
    private EmployeeRepositoryImpl employeeRepository;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(EmployeeJpaRepository.class);
        employeeRepository = new EmployeeRepositoryImpl(jpaRepository);
    }

    @Test
    void save_ShouldSaveEmployeeAndReturnEntity() {
        Employee employee = new Employee("Alice", "alice@example.com", "HR");
        EmployeeJpaEntity savedEntity = new EmployeeJpaEntity(UUID.randomUUID(), "Alice", "alice@example.com", "HR");

        when(jpaRepository.save(any(EmployeeJpaEntity.class))).thenReturn(savedEntity);

        EmployeeResponse result = employeeRepository.save(employee);

        // Captura e verifica o objeto passado para o save
        ArgumentCaptor<EmployeeJpaEntity> captor = ArgumentCaptor.forClass(EmployeeJpaEntity.class);
        verify(jpaRepository).save(captor.capture());
        EmployeeJpaEntity captured = captor.getValue();
        assertEquals("Alice", captured.getName());
        assertEquals("alice@example.com", captured.getEmail());
        assertEquals("HR", captured.getDepartment());

        // Verifica o objeto passado como parametro no create com a saida do metodo
        assertEquals(result.getName(), employee.getName());
        assertEquals(result.getEmail(), employee.getEmail());
        assertEquals(result.getDepartment(), employee.getDepartment());
    }

    @Test
    void findAll_ShouldReturnListOfEmployees() {
        List<EmployeeJpaEntity> entities = List.of(
                new EmployeeJpaEntity(UUID.randomUUID(), "Bob", "bob@example.com", "IT"),
                new EmployeeJpaEntity(UUID.randomUUID(), "Carol", "carol@example.com", "Finance")
        );

        when(jpaRepository.findAll()).thenReturn(entities);

        List<EmployeeResponse> result = employeeRepository.findAll();

        assertEquals(2, result.size());
        assertEquals("Bob", result.get(0).getName());
        assertEquals("carol@example.com", result.get(1).getEmail());
    }

    @Test
    void deleteById_ShouldCallRepositoryDelete() {
        UUID id = UUID.randomUUID();

        employeeRepository.deleteById(id);

        verify(jpaRepository, times(1)).deleteById(id);
    }
}