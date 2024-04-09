package org.controle_empregado_projeto.controle_empregado_projeto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import com.github.javafaker.Faker;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Employee;
import org.controle_empregado_projeto.controle_empregado_projeto.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

    private Employee employee;
    private Faker faker;
    private Random random = new Random();

    @Test
    @DisplayName("Testanto o save de um employee fake")
    void shouldCreateFakeEmployee(){

        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        Employee savedEmployee = employeeServiceImpl.fillEmployeeAndSave();

        verify(employeeRepository).save(any(Employee.class));

        assertNotNull(savedEmployee, "O funcionario salvo não deve ser nulo");
    }

    @Test
    void testCreate(){
        Employee employee1 = new Employee();
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);
        Employee result = employeeServiceImpl.create(new Employee());
        assertEquals(employee, result);
    }

    @Test
    void testDelete() {
        Integer empId = 1;
        Employee employee = new Employee();
        when(employeeRepository.getReferenceById(empId)).thenReturn(employee);
        Employee result = employeeServiceImpl.delete(empId);
        verify(employeeRepository, times(1)).delete(employee);
        assertEquals(employee, result);
    }

    @Test
    void testUpdate() {
        Integer empId = 1;
        Employee existingEmployee = new Employee();
        Employee updatedEmployee = new Employee();
        when(employeeRepository.getReferenceById(empId)).thenReturn(existingEmployee);
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);
        assertEquals(updatedEmployee, employeeServiceImpl.update(empId, updatedEmployee));
    }

    @Test
    void testGetById() {
        Integer empId = 1;
        Employee employee = new Employee();
        when(employeeRepository.getReferenceById(empId)).thenReturn(employee);
        assertEquals(employee, employeeServiceImpl.getById(empId));
    }

    @Test
    void testGetAll() {
        ArrayList<Employee> employees = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employees);
        assertEquals(employees, employeeServiceImpl.getAll());
    }
}
