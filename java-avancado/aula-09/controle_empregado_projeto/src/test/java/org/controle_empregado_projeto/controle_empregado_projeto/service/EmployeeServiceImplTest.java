package org.controle_empregado_projeto.controle_empregado_projeto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import com.github.javafaker.Faker;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Employee;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Project;
import org.controle_empregado_projeto.controle_empregado_projeto.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee employee;

    @Test
    void shouldCreateFakeEmployee(){

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        Employee savedEmployee = employeeServiceImpl.fillEmployeeAndSave();

        verify(employeeRepository).save(any(Employee.class));

        assertNotNull(savedEmployee, "O funcionario salvo não deve ser nulo");
    }
}
