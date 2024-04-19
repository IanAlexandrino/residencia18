package org.db_crud.db_crud.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = Escola.TABLE_NAME)
public class Escola {

    public static final String TABLE_NAME = "escola";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @OneToMany(mappedBy = "escola", fetch = FetchType.LAZY)
    private List<Aluno> alunos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "escolas_cursos", joinColumns = @JoinColumn(name = "escola_fk"),
            inverseJoinColumns = @JoinColumn(name = "curso_fk"))
    private Set<Curso> cursos = new HashSet<>();

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

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos.addAll(cursos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escola other = (Escola) o;
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
