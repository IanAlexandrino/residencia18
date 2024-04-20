package org.db_crud.db_crud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Aluno.TABLE_NAME)
public class Aluno{

    public static final String TABLE_NAME = "aluno";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula", unique = true)
    private Integer matricula;

    @NotNull(message = "Campo nome não pode ser nulo!")
    @NotEmpty(message = "Campo nome não pode ser uma string vazia!")
    @Size(min = 3, max = 60, message = "Campo nome não pode ser menor que 3 nem maior que 60!")
    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @NotNull(message = "Campo cpf não pode ser nulo!")
    @NotEmpty(message = "Campo cpf não pode ser uma string vazia!")
    @Size(min = 11, max = 14, message = "Campo cpf não pode ser menor que 11 nem maior que 14!")
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;
}
