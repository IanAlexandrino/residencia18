package org.db_crud.db_crud.controllers;

import org.db_crud.db_crud.models.Escola;
import org.db_crud.db_crud.services.EscolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/escolas")
@Validated
public class EscolaController {

    @Autowired
    private EscolaService escolaService;

    @GetMapping("/{id}")
    public ResponseEntity<Escola> retornaEscolaPorId(@PathVariable Integer id){
        Escola escola = escolaService.findById(id);
        return ResponseEntity.ok().body(escola);
    }

    @PostMapping
    public ResponseEntity<Void> criaEscola(@RequestBody Escola escola){
        this.escolaService.create(escola);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(escola.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizaEscola(
            @PathVariable Integer id,
            @RequestBody Escola escola
    ){
        escola.setId(id);
        this.escolaService.update(escola);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaEscola(@PathVariable Integer id){
        this.escolaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
