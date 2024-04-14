package org.db_crud.db_crud.model;

import jakarta.persistence.*;

@Entity
public class Aluno{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricula;
    private String nome;
    private String cpf;
    private String curso;
    @ManyToOne
    private Escola escola;

    public Aluno() {
    }

    public Aluno(Integer matricula, String nome, String cpf, String curso, Escola escola) {
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}
