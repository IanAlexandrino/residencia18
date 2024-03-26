package org.db_crud.db_crud.controller.dto;

import org.db_crud.db_crud.model.Aluno;

public class AlunoDTO {

    private Integer Matricula;
    private String Nome;
    private String Cpf;
    private String Curso;
    private EscolaDTO EscolaDTO;

    public AlunoDTO() {
    }

    public AlunoDTO(Integer matricula, String nome, String cpf, String curso, EscolaDTO escolaDTO) {
        this.Matricula = matricula;
        this.Nome = nome;
        this.Cpf = cpf;
        this.Curso = curso;
        this.EscolaDTO = escolaDTO;
    }

    public AlunoDTO(Aluno aluno) {
        this.Matricula = aluno.getMatricula();
        this.Nome = aluno.getNome();
        this.Cpf = aluno.getCpf();
        this.Curso = aluno.getCurso();
        this.EscolaDTO = new EscolaDTO(aluno.getEscola());
    }

    public Integer getMatricula() {
        return Matricula;
    }

    public String getNome() {
        return Nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public String getCurso() {
        return Curso;
    }

    public EscolaDTO getEscolaDTO() {
        return EscolaDTO;
    }
}
