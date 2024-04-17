package org.db_crud.db_crud.services;

import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso findById(Integer id){
        Optional<Curso> curso = this.cursoRepository.findById(id);
        return curso.orElseThrow(() -> new RuntimeException(
                "Curso não encontrado! Id: " + id + ", Tipo: " + Curso.class.getName()
        ));
    }

    public List<Curso> findAllByEscolaId(Integer escoolaId){
        return this.cursoRepository.findByEscola_Id(escoolaId);
    }

    @Transactional
    public Curso create(Curso curso){
        curso.setId(null);
        curso = this.cursoRepository.save(curso);
        return curso;
    }

    @Transactional
    public Curso update(Curso curso){
        Curso novoCurso = this.findById(curso.getId());
        novoCurso.setConteudo(curso.getConteudo());
        return this.cursoRepository.save(novoCurso);
    }

    @Transactional
    public void delete(Integer id){
        this.findById(id);
        try {
            this.cursoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possível excluir pois há Entidades relacionadas!"
            );
        }
    }

}
