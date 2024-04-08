package org.controle_empregado_projeto.controle_empregado_projeto.service;

import com.github.javafaker.Faker;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Employee;
import org.controle_empregado_projeto.controle_empregado_projeto.model.Project;

import java.util.Set;

public interface EmployeeService {

    Employee fillEmployeeAndSave();

}
