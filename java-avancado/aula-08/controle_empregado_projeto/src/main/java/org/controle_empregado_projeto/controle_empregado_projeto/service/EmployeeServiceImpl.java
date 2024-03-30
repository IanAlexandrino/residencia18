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
    public Employee fillEmployee(Employee employee, Project project,Set<Employee> employees, Set<Project> projects) {
        Faker faker = new Faker();
        employee = new Employee();
        project = new Project();
        employees = new HashSet<>();
        projects = new HashSet<>();

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
