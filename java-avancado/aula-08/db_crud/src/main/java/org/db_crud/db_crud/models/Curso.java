package org.db_crud.db_crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Curso.TABLE_NAME)
public class Curso {

    public static final String TABLE_NAME = "curso";

    public interface CreateCurso{}
    public interface UpdateCurso{}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @NotNull(groups = CreateCurso.class, message = "Campo nome não pode ser nulo!")
    @NotEmpty(groups = CreateCurso.class, message = "Campo nome não pode ser uma string vazia!")
    @Size(groups = CreateCurso.class, min = 5, max = 35, message = "Campo nome não pode ser menor que 5 nem maior que 35!")
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @NotNull(groups = {CreateCurso.class, UpdateCurso.class}, message = "Campo conteudo não pode ser nulo!")
    @NotEmpty(groups = {CreateCurso.class, UpdateCurso.class}, message = "Campo conteudo não pode ser uma string vazia!")
    @Size(groups = {CreateCurso.class, UpdateCurso.class}, min = 10, max = 200, message = "Campo conteudo não pode ser menor que 10 nem maior que 200!")
    @Column(name = "conteudo", nullable = false)
    private String conteudo;

    @JsonIgnore
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Aluno> alunos;

    @JsonIgnore
    @ManyToMany(mappedBy = "cursos", fetch = FetchType.LAZY)
    private Set<Escola> escolas = new HashSet<>();

    public Curso(Integer id, String nome, String conteudo) {
        this.id = id;
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public void setEscolas(Set<Escola> escolas) {
        this.escolas.addAll(escolas);
    }
}
