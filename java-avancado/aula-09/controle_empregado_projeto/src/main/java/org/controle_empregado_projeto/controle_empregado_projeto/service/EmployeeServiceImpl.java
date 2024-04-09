package org.controle_empregado_projeto.controle_empregado_projeto.service;

import com.github.javafaker.Faker;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Employee;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Project;
import org.controle_empregado_projeto.controle_empregado_projeto.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee fillEmployeeAndSave() {
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

        employeeRepository.save(employee);

        return employee;
    }

    @Override
    public Employee create(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee delete(Integer empId){
        Employee employee = employeeRepository.getReferenceById(empId);
        employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public ArrayList<Employee> getAll(){
        return (ArrayList<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer empId){
        return employeeRepository.getReferenceById(empId);
    }

    @Override
    public Employee update(Integer empId, Employee employee_body){
        Employee employee = employeeRepository.getReferenceById(empId);
        employee.setName(employee_body.getName());
        employee.setEmail(employee_body.getEmail());
        employee.setTechnicalSkill(employee_body.getTechnicalSkill());
        employee.setProjects(employee_body.getProjects());
        employeeRepository.save(employee);

        return employee_body;
    }
}
