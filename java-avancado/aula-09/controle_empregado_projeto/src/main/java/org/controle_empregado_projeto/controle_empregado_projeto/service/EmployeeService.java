package org.controle_empregado_projeto.controle_empregado_projeto.service;

import com.github.javafaker.Faker;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Employee;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Project;

import java.util.ArrayList;
import java.util.Set;

public interface EmployeeService {

    Employee fillEmployeeAndSave();
    Employee create(Employee employee);
    Employee delete(Integer empId);
    ArrayList<Employee> getAll();
    Employee getById(Integer empId);
    Employee update(Integer empId, Employee employee_body);

}
