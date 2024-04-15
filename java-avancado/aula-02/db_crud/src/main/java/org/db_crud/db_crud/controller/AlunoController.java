package org.db_crud.db_crud.controller;

import org.db_crud.db_crud.model.Aluno;
import org.db_crud.db_crud.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
@Validated
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/{matricula}")
    public ResponseEntity<Aluno> retornaAlunoPorId(@PathVariable Integer matricula){
        Aluno aluno = this.alunoService.findByMatricula(matricula);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<Aluno> criaAluno(@RequestBody Aluno aluno){
        this.alunoService.create(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{matricula}").buildAndExpand(aluno.getMatricula()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<?> atualizaAluno(
            @PathVariable Integer matricula,
            @RequestBody Aluno aluno
    ){
        aluno.setMatricula(matricula);
        this.alunoService.update(aluno);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<?> deletaAluno(@PathVariable Integer matricula){
        this.alunoService.delete(matricula);
        return ResponseEntity.noContent().build();
    }

}
