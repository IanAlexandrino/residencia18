package org.db_crud.db_crud.repository;

import org.db_crud.db_crud.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
