package org.db_crud.db_crud.services;

import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.models.Escola;
import org.db_crud.db_crud.repositories.CursoRepository;
import org.db_crud.db_crud.repositories.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private CursoService cursoService;

    public Escola findById(Integer id){
        Optional<Escola> escola = this.escolaRepository.findById(id);
        return escola.orElseThrow(() -> new RuntimeException(
                "Escola não encontrada! Id: " + id + ", Tipo: " + Escola.class.getName()
        ));
    }

    @Transactional
    public Escola create(Escola escola){
        escola.setId(null);
        escola = this.escolaRepository.save(escola);
        return escola;
    }

    @Transactional
    public Escola update(Escola escola){
        Escola novaEscola = this.findById(escola.getId());
        novaEscola.setNome(escola.getNome());
        novaEscola.setLocalizacao(escola.getLocalizacao());
        return this.escolaRepository.save(novaEscola);
    }

    @Transactional
    public void delete(Integer id){
        this.findById(id);
        try {
            this.escolaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possível excluir pois há Entidades relacionadas!"
            );
        }
    }

    @Transactional
    public void assignEscolaToCursoById(Integer escolaId, Integer cursoId){
        Escola escola = this.findById(escolaId);
        Curso curso = this.cursoService.findById(cursoId);
        Set<Curso> cursos = new HashSet<>();
        cursos.add(curso);
        escola.setCursos(cursos);
        this.escolaRepository.save(escola);
    }
}
