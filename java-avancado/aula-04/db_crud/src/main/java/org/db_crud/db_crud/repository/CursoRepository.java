package org.db_crud.db_crud.repository;

import org.db_crud.db_crud.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    List<Curso> findByEscola_Id (Integer id);

}
