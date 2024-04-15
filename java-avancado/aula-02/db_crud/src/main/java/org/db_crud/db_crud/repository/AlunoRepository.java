package org.db_crud.db_crud.repository;

import org.db_crud.db_crud.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findByEscola_Id (Integer id);

}
