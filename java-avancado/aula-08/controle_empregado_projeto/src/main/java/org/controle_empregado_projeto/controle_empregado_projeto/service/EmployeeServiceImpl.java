package org.controle_empregado_projeto.controle_empregado_projeto.service;

import com.github.javafaker.Faker;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Employee;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Project;
import org.controle_empregado_projeto.controle_empregado_projeto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee fillEmployee() {
        Faker faker = new Faker();
        Employee employee = new Employee();
        Project project = new Project();
        Set<Employee> employees = new HashSet<>();
        Set<Project> projects = new HashSet<>();

        employee.setName(faker.name().fullName());
        employee.setEmail(faker.internet().emailAddress());
        employee.setTechnicalSkill(faker.job().position());
        employee.setProjects(projects);

        project.setProjectName(faker.company().name());
        project.setTechnologyUsed(faker.company().industry());
        project.setEmployees(employees);

        return employee;
    }
}
