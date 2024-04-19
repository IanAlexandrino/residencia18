package org.db_crud.db_crud.services;

import jakarta.persistence.EntityNotFoundException;
import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.repositories.CursoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    public static final Logger log = LoggerFactory.getLogger(CursoService.class);

    @Autowired
    private CursoRepository cursoRepository;

    public Curso findById(Integer id){
        Optional<Curso> curso = this.cursoRepository.findById(id);
        if (curso.isPresent()){
            log.info("Curso encontrado com sucesso!");
        }
        return curso.orElseThrow(() -> new EntityNotFoundException(
                "Curso não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()
        ));
    }

    public List<Curso> findAllByEscolaId(Integer escoolaId){
        return this.cursoRepository.findByEscolas_Id(escoolaId);
    }

    @Transactional
    public Curso create(Curso curso){
        curso.setId(null);
        curso = this.cursoRepository.save(curso);
        log.info("Novo Curso inserido!");
        return curso;
    }

    @Transactional
    public Curso update(Curso curso){
        Curso novoCurso = this.findById(curso.getId());
        novoCurso.setConteudo(curso.getConteudo());
        log.info("Entidade Curso atualizada!");
        return this.cursoRepository.save(novoCurso);
    }

    @Transactional
    public void delete(Integer id){
        this.findById(id);
        try {
            this.cursoRepository.deleteById(id);
            log.info("Entidade Curso foi deletada!");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possível excluir pois há Entidades relacionadas!"
            );
        }
    }
}
