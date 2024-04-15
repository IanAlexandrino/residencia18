package org.db_crud.db_crud.services;

import org.db_crud.db_crud.model.Escola;
import org.db_crud.db_crud.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EscolaService {

    @Autowired
    private EscolaRepository escolaRepository;

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
        Escola novaEscola = findById(escola.getId());
        novaEscola.setNome(escola.getNome());
        novaEscola.setLocalizacao(escola.getLocalizacao());
        return this.escolaRepository.save(novaEscola);
    }

    public void delete(Integer id){
        findById(id);
        try {
            this.escolaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Não é possível excluir pois há Entidades relacionadas!"
            );
        }
    }

}
