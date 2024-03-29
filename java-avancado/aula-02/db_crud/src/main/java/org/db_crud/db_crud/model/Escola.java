package org.db_crud.db_crud.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Escola {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String localizacao;
    @OneToMany(mappedBy = "escola", cascade = CascadeType.ALL)
    private List<Aluno> alunos;

    public Escola() {
    }

    public Escola(Integer id, String nome, String localizacao, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.alunos = alunos;
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

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
