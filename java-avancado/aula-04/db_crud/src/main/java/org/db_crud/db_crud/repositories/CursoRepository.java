package org.db_crud.db_crud.repositories;

import org.db_crud.db_crud.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    List<Curso> findByEscolas_Id (Integer id);

}
