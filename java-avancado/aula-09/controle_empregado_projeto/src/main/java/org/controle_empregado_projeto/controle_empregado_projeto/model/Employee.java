package org.controle_empregado_projeto.controle_empregado_projeto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull(message = "Name can't be null")
    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @NotNull(message = "Technical skill can't be null")
    @Column(name = "technicalSkill")
    private String technicalSkill;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    public Employee(String name, String email, String technicalSkill) {
        this.name = name;
        this.email = email;
        this.technicalSkill = technicalSkill;
    }

    public Employee(Employee employee){
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.technicalSkill = employee.getTechnicalSkill();
        this.projects = employee.getProjects();
    }

}
