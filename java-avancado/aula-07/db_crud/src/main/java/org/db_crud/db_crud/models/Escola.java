package org.db_crud.db_crud.models;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Table(name = Escola.TABLE_NAME)
public class Escola {

    public static final String TABLE_NAME = "escola";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @OneToMany(mappedBy = "escola", fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Aluno> alunos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JoinTable(name = "escolas_cursos", joinColumns = @JoinColumn(name = "escola_fk"),
            inverseJoinColumns = @JoinColumn(name = "curso_fk"))
    private Set<Curso> cursos = new HashSet<>();

    public Escola(Integer id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public void setCursos(Set<Curso> cursos) {
        this.cursos.addAll(cursos);
    }
}
