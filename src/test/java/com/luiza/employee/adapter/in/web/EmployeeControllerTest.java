package com.luiza.employee.adapter.in.web;

import com.luiza.employee.adapter.out.persistence.EmployeeJpaEntity;
import com.luiza.employee.application.usecase.EmployeeService;
import com.luiza.employee.domain.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    private EmployeeService employeeService;
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(employeeService);
    }

    @Test
    void listAll_ShouldReturnListOfEmployees() {
        List<Employee> mockEmployees = List.of(new Employee("John Doe","john@gmail.com", "Engineering"));
        when(employeeService.getAll()).thenReturn(mockEmployees);

        ResponseEntity<List<Employee>> response = employeeController.listAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEmployees, response.getBody());
    }

    @Test
    void create_ShouldReturnCreatedEmployee() {
        Employee employee = new Employee("Jane Doe","Jane@gmail.com", "HR");
        EmployeeJpaEntity savedEntity = new EmployeeJpaEntity(UUID.randomUUID(), employee.getName(),employee.getEmail(), employee.getDepartment());

        when(employeeService.create(employee)).thenReturn(savedEntity);

        ResponseEntity<EmployeeJpaEntity> response = employeeController.create(employee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedEntity, response.getBody());
    }

    @Test
    void delete_ShouldReturnNoContent() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = employeeController.delete(id);

        verify(employeeService, times(1)).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}