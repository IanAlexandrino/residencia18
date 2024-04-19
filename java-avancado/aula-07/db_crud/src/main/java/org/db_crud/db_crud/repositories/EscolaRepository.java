package org.db_crud.db_crud.repositories;

import org.db_crud.db_crud.models.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Integer> {
}
