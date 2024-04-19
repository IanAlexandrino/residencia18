package org.db_crud.db_crud.services;

import jakarta.persistence.EntityNotFoundException;
import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.models.Escola;
import org.db_crud.db_crud.repositories.CursoRepository;
import org.db_crud.db_crud.repositories.EscolaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EscolaService {

    public static final Logger log = LoggerFactory.getLogger(EscolaService.class);

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    public Escola findById(Integer id){
        Optional<Escola> escola = this.escolaRepository.findById(id);
        if (escola.isPresent()){
            log.info("Escola encontrada com sucesso!");
        }
        return escola.orElseThrow(() -> new EntityNotFoundException(
                "Escola não encontrada! Id: " + id + ", Tipo: " + Escola.class.getName()
        ));
    }

    @Transactional
    public Escola create(Escola escola){
        escola.setId(null);
        escola = this.escolaRepository.save(escola);
        log.info("Nova Escola inserida!");
        return escola;
    }

    @Transactional
    public Escola update(Escola escola){
        Escola novaEscola = this.findById(escola.getId());
        novaEscola.setNome(escola.getNome());
        novaEscola.setLocalizacao(escola.getLocalizacao());
        log.info("Entidade Escola atualizada!");
        return this.escolaRepository.save(novaEscola);
    }

    @Transactional
    public void delete(Integer id){
        this.findById(id);
        try {
            this.escolaRepository.deleteById(id);
             log.info("Entidade Escola foi deletada!");
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possível excluir pois há Entidades relacionadas!"
            );
        }
    }

    @Transactional
    public void assignCursoToEscolaById(Integer cursoId, Integer escolaId){
        Curso curso = this.cursoService.findById(cursoId);
        Escola escola = this.findById(escolaId);
        Set<Curso> cursos = new HashSet<>();
        cursos.add(curso);
        escola.setCursos(cursos);
        this.escolaRepository.save(escola);
        log.info("Um Curso foi atribuído para Escola!");
    }
}
