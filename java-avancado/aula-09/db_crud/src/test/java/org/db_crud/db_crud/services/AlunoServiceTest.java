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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private CursoService cursoService;

    @Mock
    private EscolaService escolaService;

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
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            alunoService.findByMatricula(5433333);
        });
    }

    @Test
    void shouldSuccessFindAllByCursoId(){
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno());
        alunos.add(new Aluno());
        Mockito.when(alunoRepository.findByCurso_Id(ModelMocks.idCurso)).thenReturn(alunos);
        List<Aluno> alunosFound = alunoService.findAllByCursoId(ModelMocks.idCurso);
        Assertions.assertEquals(2, alunosFound.size());
    }

    @Test
    void shouldReturnEmptyAlunosListWithCursoId(){
        List<Aluno> alunosFound = alunoService.findAllByCursoId(234234);
        Assertions.assertTrue(alunosFound.isEmpty());
    }

    @Test
    void shouldSuccessFindAllByEscolaId(){
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno());
        alunos.add(new Aluno());
        Mockito.when(alunoRepository.findByEscola_Id(ModelMocks.idEscola)).thenReturn(alunos);
        List<Aluno> alunosFound = alunoService.findAllByEscolaId(ModelMocks.idEscola);
        Assertions.assertEquals(2, alunosFound.size());
    }

    @Test
    void shouldReturnEmptyAlunosListWithEscolaId(){
        List<Aluno> alunosFound = alunoService.findAllByEscolaId(234234);
        Assertions.assertTrue(alunosFound.isEmpty());
    }

    @Test
    void shouldSuccessCreateAluno(){
        Mockito.when(cursoService.findById(ModelMocks.idCurso)).thenReturn(curso);
        Mockito.when(escolaService.findById(ModelMocks.idEscola)).thenReturn(escola);
        Mockito.when(alunoRepository.save(Mockito.any(Aluno.class))).thenReturn(aluno);
        Aluno alunoSalvo = alunoService.create(aluno);
        Mockito.verify(alunoRepository).save(Mockito.any(Aluno.class));
        Assertions.assertNotNull(alunoSalvo);
        Assertions.assertEquals(aluno.getNome(), alunoSalvo.getNome());
    }
}
