package org.db_crud.db_crud.services;

import jakarta.persistence.EntityNotFoundException;
import org.db_crud.db_crud.models.Aluno;
import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.models.Escola;
import org.db_crud.db_crud.repositories.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    public static final Logger log = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EscolaService escolaService;

    public Aluno findByMatricula(Integer matricula){
        Optional<Aluno> aluno = this.alunoRepository.findById(matricula);
        if (aluno.isPresent()){
            log.info("Aluno encontrado com sucesso!");
        }
        return aluno.orElseThrow(() -> new EntityNotFoundException(
                "Aluno não encontrado! Matrícula: " + matricula + ", Tipo: " + Aluno.class.getName()
        ));
    }

    public List<Aluno> findAllByCursoId(Integer cursoId){
        return this.alunoRepository.findByCurso_Id(cursoId);
    }

    public List<Aluno> findAllByEscolaId(Integer escolaId){
        return this.alunoRepository.findByEscola_Id(escolaId);
    }

    @Transactional
    public Aluno create(Aluno aluno){
        Curso curso = this.cursoService.findById(aluno.getCurso().getId());
        Escola escola = this.escolaService.findById(aluno.getEscola().getId());
        aluno.setMatricula(null);
        aluno.setCurso(curso);
        aluno.setEscola(escola);
        aluno = this.alunoRepository.save(aluno);
        log.info("Novo Aluno inserido!");
        return aluno;
    }

    @Transactional
    public Aluno update(Aluno aluno){
        Aluno novoAluno = this.findByMatricula(aluno.getMatricula());
        novoAluno.setCurso(aluno.getCurso());
        novoAluno.setEscola(aluno.getEscola());
        log.info("Entidade Aluno atualizada!");
        return this.alunoRepository.save(novoAluno);
    }

    @Transactional
    public void delete(Integer matricula){
        this.findByMatricula(matricula);
        try {
            this.alunoRepository.deleteById(matricula);
            log.info("Entidade Aluno foi deletada!");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possível excluir pois há Entidades relacionadas!"
            );
        }
    }
}
