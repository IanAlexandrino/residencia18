package org.db_crud.db_crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
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
    private Set<Escola> escolas;

    public Curso() {
    }

    public Curso(Integer id, String nome, String conteudo) {
        this.id = id;
        this.nome = nome;
        this.conteudo = conteudo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Escola> getEscolas() {
        return escolas;
    }

    public void setEscolas(Set<Escola> escolas) {
        this.escolas.addAll(escolas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso other = (Curso) o;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
