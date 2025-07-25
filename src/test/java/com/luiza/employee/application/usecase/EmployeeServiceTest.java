package com.luiza.employee.application.usecase;

import com.luiza.employee.domain.model.Employee;
import com.luiza.employee.domain.model.EmployeeResponse;
import com.luiza.employee.domain.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

class EmployeeServiceTest {

    private EmployeeRepository repository;
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        repository = mock(EmployeeRepository.class);
        service = new EmployeeService(repository);
    }

    @Test
    void shouldCreateEmployee() {
        // Arrange
        Employee employee = new Employee("Joao", "joao@gmail.com", "TI");
        EmployeeResponse expectedResponse = new EmployeeResponse(UUID.randomUUID(),"Joao", "joao@gmail.com", "TI");

        when(repository.save(employee)).thenReturn(expectedResponse);

        // Act
        EmployeeResponse result = service.create(employee);

        // Assert
        assertEquals(expectedResponse, result);
        verify(repository).save(employee);
    }

    @Test
    void shouldReturnAllEmployees() {
        // Arrange
        List<EmployeeResponse> expectedList = Arrays.asList(
                new EmployeeResponse(UUID.randomUUID(),"Joao", "joao@gmail.com", "TI"),
                new EmployeeResponse(UUID.randomUUID(),"Maria", "Mariagit s@gmail.com", "TI")
        );

        when(repository.findAll()).thenReturn(expectedList);

        // Act
        List<EmployeeResponse> result = service.getAll();

        // Assert
        assertEquals(expectedList, result);
        verify(repository).findAll();
    }

    @Test
    void shouldDeleteEmployeeById() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        service.delete(id);

        // Assert
        verify(repository).deleteById(id);
    }
}