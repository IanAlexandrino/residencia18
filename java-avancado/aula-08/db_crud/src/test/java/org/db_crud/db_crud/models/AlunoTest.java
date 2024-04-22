package org.db_crud.db_crud.models;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class AlunoTest {

    Escola escola1;
    Curso curso1;
    Aluno aluno1;

    static Faker faker = new Faker();

    public class ModelMock{
        public static final int idEscola = faker.number().randomDigit();
        public static final int idCurso = faker.number().randomDigit();
        public static final int idAluno = faker.number().randomDigit();
        public static final String nomeEscola = faker.company().name();
        public static final String nomeCurso = faker.educator().course();
        public static final String nomeAluno = faker.name().fullName();
        public static final String endereco = faker.address().streetAddress();
        public static final String cpf = faker.idNumber().valid();
        public static final String conteudo = faker.educator().course() + faker.educator().course() + faker.educator().course();
    }

    @BeforeEach
    public void prencheMoks(){
        escola1 = new Escola(ModelMock.idEscola, ModelMock.nomeEscola, ModelMock.endereco);
        curso1 = new Curso(ModelMock.idCurso, ModelMock.nomeCurso, ModelMock.conteudo);
        aluno1 = new Aluno(ModelMock.idAluno, ModelMock.nomeAluno, ModelMock.cpf, curso1, escola1);
    }

    @Test
    public void testaConstrutoresEGetters(){
        assertEquals(ModelMock.idAluno, aluno1.getMatricula());
        assertEquals(ModelMock.nomeAluno, aluno1.getNome());
        assertEquals(ModelMock.cpf, aluno1.getCpf());
        assertEquals(curso1, aluno1.getCurso());
        assertEquals(escola1, aluno1.getEscola());
    }

    @Test
    public void testaEqualsEHashCode(){

        Escola escola2 = new Escola(faker.number().randomDigit(), faker.company().name(), faker.address().streetAddress());
        Curso curso2 = new Curso(faker.number().randomDigit(), faker.educator().course(), faker.educator().course() + faker.educator().course() + faker.educator().course());
        Aluno aluno2 = new Aluno(aluno1.getMatricula(), aluno1.getNome(), aluno1.getCpf(), curso1, escola1);
        Aluno aluno3 = new Aluno(faker.number().randomDigit(), faker.name().fullName(), faker.idNumber().valid(), curso2, escola2);

        assertEquals(aluno1, aluno2);
        assertEquals(aluno1.hashCode(), aluno2.hashCode());
        assertNotEquals(aluno1, aluno3);
        assertNotEquals(aluno1.hashCode(), aluno3.hashCode());
    }
}
