package org.db_crud.db_crud.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    @Test
    public void testaConstrutoresEGetters(){

        Escola escola = new Escola(1, "Escola Teste", "Localização Teste");
        Curso curso = new Curso(3, "Engenharia", "Administração, Automação Industrial, Controle Estatístico");
        Aluno aluno = new Aluno(123456, "Fulano", "123.456.789-00", curso, escola);

        assertEquals(123456, aluno.getMatricula());
        assertEquals("Fulano", aluno.getNome());
        assertEquals("123.456.789-00", aluno.getCpf());
        assertEquals(curso, aluno.getCurso());
        assertEquals(escola, aluno.getEscola());
    }

    @Test
    public void testaEqualsEHashCode(){

        Escola escola1 = new Escola(1, "Escola Teste", "Localização Teste");
        Escola escola2 = new Escola(2, "Outra Escola", "Outra Localização");
        Curso curso1 = new Curso(4, "Engenharia", "Administração, Automação Industrial, Controle Estatístico");
        Curso curso2 = new Curso(3, "Medicina", "Administração, Automação Industrial, Controle Estatístico");
        Aluno aluno1 = new Aluno(123456, "Fulano", "123.456.789-00", curso1, escola1);
        Aluno aluno2 = new Aluno(123456, "Fulano", "123.456.789-00", curso1, escola1);
        Aluno aluno3 = new Aluno(654321, "Ciclano", "987.654.321-00", curso2, escola2);

        assertEquals(aluno1, aluno2);
        assertEquals(aluno1.hashCode(), aluno2.hashCode());
        assertNotEquals(aluno1, aluno3);
        assertNotEquals(aluno1.hashCode(), aluno3.hashCode());
    }
}
