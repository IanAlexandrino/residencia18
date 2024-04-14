package org.db_crud.db_crud.controller.form;

import org.db_crud.db_crud.model.Aluno;
import org.db_crud.db_crud.model.Escola;

public class EscolaForm {

    private String nome;
    private String localizacao;

    public EscolaForm() {
    }

    public EscolaForm(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
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

    public Escola criaEscola(){
        return new Escola(null, nome, localizacao);
    }
}
