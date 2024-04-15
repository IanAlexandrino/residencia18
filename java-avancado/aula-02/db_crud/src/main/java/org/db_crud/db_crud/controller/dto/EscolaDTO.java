package org.db_crud.db_crud.controller.dto;

import org.db_crud.db_crud.model.Escola;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EscolaDTO {

    private Integer Id;
    private String Nome;
    private String Localizacao;
    private List<AlunoDTO> alunos = new ArrayList<>();

    public EscolaDTO() {
    }

    public EscolaDTO(Integer id, String nome, String localizacao, List<AlunoDTO> alunos) {
        this.Id = id;
        this.Nome = nome;
        this.Localizacao = localizacao;
        this.alunos = alunos;
    }

    public EscolaDTO(Escola escola){

        this.Id = escola.getId();
        this.Nome = escola.getNome();
        this.Localizacao = escola.getLocalizacao();
        this.alunos.addAll(escola.getAlunos().stream().map(AlunoDTO::new).toList());

    }

    public Integer getId() {
        return Id;
    }

    public String getNome() {
        return Nome;
    }

    public String getLocalizacao() {
        return Localizacao;
    }

    public List<AlunoDTO> getAlunos() {
        return alunos;
    }

}
