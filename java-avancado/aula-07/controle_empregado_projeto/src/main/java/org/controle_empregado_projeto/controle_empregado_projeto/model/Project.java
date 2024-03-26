package org.controle_empregado_projeto.controle_empregado_projeto.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;


import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @NotNull(message = "Name can't be null")
    @Column(name = "projectName")
    private String projectName;

    @NotNull(message = "Technology used can't be null")
    @Column(name = "technologyUsed")
    private String technologyUsed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "EMPLOYEE_PROJECT_MAPPING", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees;

    public Project(String projectName, String technologyUsed) {
        this.projectName = projectName;
        this.technologyUsed = technologyUsed;
    }

}
