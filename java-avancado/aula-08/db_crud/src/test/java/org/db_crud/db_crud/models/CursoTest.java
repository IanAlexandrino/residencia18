package org.db_crud.db_crud.models;

import com.github.javafaker.Faker;
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

    private static Faker faker = new Faker();

    public class ModelMock{
        public static final int idEscola = faker.number().randomDigit();
        public static final int idCurso = faker.number().randomDigit();
        public static final String nomeEscola = faker.company().name();
        public static final String nomeCurso = faker.educator().course();
        public static final String endereco = faker.address().streetAddress();
        public static final String conteudo = faker.educator().course() + faker.educator().course() + faker.educator().course();
    }

    @BeforeEach
    void setUp() {
        curso1 = new Curso(ModelMock.idCurso, ModelMock.nomeCurso, ModelMock.conteudo);
        curso2 = new Curso(faker.number().randomDigit(), faker.educator().course(), faker.educator().course() + faker.educator().course() + faker.educator().course());
        curso3 = new Curso(curso1.getId(), curso1.getNome(), curso1.getConteudo());

        escola1 = new Escola(ModelMock.idEscola, ModelMock.nomeEscola, ModelMock.endereco);
        escola2 = new Escola(faker.number().randomDigit(), faker.company().name(), faker.address().streetAddress());
        escola3 = new Escola(escola1.getId(), escola1.getNome(), escola1.getLocalizacao());

        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno(faker.number().randomDigit(), faker.name().fullName(), faker.idNumber().valid(), curso1, escola1));
        alunos.add(new Aluno(faker.number().randomDigit(), faker.name().fullName(), faker.idNumber().valid(), curso1, escola1));
        curso1.setAlunos(alunos);

        Set<Escola> escolas = new HashSet<>();
        escolas.add(escola1);
        escolas.add(escola2);
        curso1.setEscolas(escolas);
        curso3.setEscolas(escolas);
    }

    @Test
    void testEquals() {
        assertEquals(curso1.getId(), curso3.getId());
        assertNotEquals(curso1.getId(), curso2.getId());
    }

    @Test
    void testHashCode() {
        assertEquals(curso1.hashCode(), curso3.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(ModelMock.idCurso, curso1.getId());
        assertEquals(ModelMock.nomeCurso, curso1.getNome());
        assertEquals(ModelMock.conteudo, curso1.getConteudo());

        assertEquals(2, curso1.getAlunos().size());

        assertEquals(2, curso1.getEscolas().size());
    }
}
