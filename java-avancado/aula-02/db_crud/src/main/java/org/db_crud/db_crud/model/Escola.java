package org.db_crud.db_crud.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Escola {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String localizacao;
    @OneToMany
    private List<Aluno> alunos = new ArrayList<>();

    public Escola() {
    }

    public Escola(Integer id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Aluno aluno) {
        this.alunos.add(aluno);
    }
}
