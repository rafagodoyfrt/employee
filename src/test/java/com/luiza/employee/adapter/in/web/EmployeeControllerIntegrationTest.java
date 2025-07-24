package com.luiza.employee.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luiza.employee.application.usecase.EmployeeService;
import com.luiza.employee.domain.model.Employee;
import com.luiza.employee.domain.model.EmployeeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listAll_ShouldReturn200WithEmployees() throws Exception {
        List<EmployeeResponse> employees = List.of(new EmployeeResponse(UUID.randomUUID(),"Alice","alice@gmailcom", "Finance"));
        when(employeeService.getAll()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void create_ShouldReturn201() throws Exception {
        Employee employee = new Employee("Bob","bob@gmail.com", "IT");
        EmployeeResponse entity = new EmployeeResponse(UUID.randomUUID(), employee.getName(), employee.getEmail(), employee.getDepartment());

        when(employeeService.create(any())).thenReturn(entity);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    @Test
    void delete_ShouldReturn204() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(employeeService).delete(id);

        mockMvc.perform(delete("/employees/{id}", id))
                .andExpect(status().isNoContent());
    }
}
