package org.db_crud.db_crud.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = Aluno.TABLE_NAME)
public class Aluno{

    public static final String TABLE_NAME = "aluno";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula", unique = true)
    private Integer matricula;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;

    public Aluno() {
    }

    public Aluno(Integer matricula, String nome, String cpf, Curso curso, Escola escola) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.curso = curso;
        this.escola = escola;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno other = (Aluno) o;
        return Objects.equals(this.matricula, other.matricula);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.matricula == null) ? 0 : this.matricula.hashCode());
        return result;
    }
}
