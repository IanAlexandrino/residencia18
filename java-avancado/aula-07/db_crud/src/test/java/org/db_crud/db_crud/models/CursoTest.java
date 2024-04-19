package org.db_crud.db_crud.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CursoTest {

    private Curso curso1;
    private Curso curso2;
    private Curso curso3;

    private Escola escola1;
    private Escola escola2;
    private Escola escola3;

    @BeforeEach
    void setUp() {
        curso1 = new Curso(1, "Matemática", "Conteúdo de Matemática");
        curso2 = new Curso(2, "Português", "Conteúdo de Português");
        curso3 = new Curso(1, "Matemática", "Conteúdo de Matemática");

        escola1 = new Escola(1, "Escola Veja a vida", "Rua pararara");
        escola2 = new Escola(2, "Escola Republicana", "Rua pararara");
        escola3 = new Escola(1, "Escola Pública", "Rua pararara");

        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno(1, "João", "34234234", curso1, escola1));
        alunos.add(new Aluno(2, "Maria", "67867893646", curso1, escola1));
        curso1.setAlunos(alunos);

        Set<Escola> escolas = new HashSet<>();
        escolas.add(escola1);
        escolas.add(escola2);
        curso1.setEscolas(escolas);
        curso3.setEscolas(escolas);
    }

    @Test
    void testEquals() {
        assertEquals(curso1, curso3);
        assertNotEquals(curso1, curso2);
    }

    @Test
    void testHashCode() {
        assertEquals(curso1.hashCode(), curso3.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(Integer.valueOf(1), curso1.getId());
        assertEquals("Matemática", curso1.getNome());
        assertEquals("Conteúdo de Matemática", curso1.getConteudo());

        assertEquals(2, curso1.getAlunos().size());

        assertEquals(2, curso1.getEscolas().size());
    }
}
