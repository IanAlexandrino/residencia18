package org.db_crud.db_crud.services;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityNotFoundException;
import org.db_crud.db_crud.models.Aluno;
import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.models.Escola;
import org.db_crud.db_crud.repositories.AlunoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;
    private Escola escola;
    private Curso curso;
    private static Faker faker = new Faker();

    public class ModelMocks {
        public static Integer idAluno = faker.number().randomDigit();
        public static Integer idEscola = faker.number().randomDigit();
        public static Integer idCurso = faker.number().randomDigit();
        public static String nomeAluno = faker.name().fullName();
        public static String cpfAluno = faker.idNumber().valid();

    }

    @BeforeEach
    void setUp(){
        escola = new Escola();
        escola.setId(ModelMocks.idEscola);
        curso = new Curso();
        curso.setId(ModelMocks.idCurso);
        aluno = new Aluno(ModelMocks.idAluno, ModelMocks.nomeAluno, ModelMocks.cpfAluno, curso, escola);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSuccessFindByMatricula(){
        Mockito.when(alunoRepository.findById(ModelMocks.idAluno)).thenReturn(Optional.of(aluno));
        Aluno alunoFound = alunoService.findByMatricula(ModelMocks.idAluno);
        Assertions.assertEquals(aluno, alunoFound);
    }

    @Test
    void shouldThrowEntityNotFoundException(){
        Mockito.when(alunoRepository.findById(5433333)).thenThrow(new EntityNotFoundException(
                "Aluno não encontrado! Matrícula: " + 5433333 + ", Tipo: " + Aluno.class.getName()

        ));
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            alunoService.findByMatricula(5433333);
        });
    }

}
