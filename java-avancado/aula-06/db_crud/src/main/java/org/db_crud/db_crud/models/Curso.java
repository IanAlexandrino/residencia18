package org.db_crud.db_crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

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
