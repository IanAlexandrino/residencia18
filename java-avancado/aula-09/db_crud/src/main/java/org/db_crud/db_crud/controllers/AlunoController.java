package org.db_crud.db_crud.controllers;

import jakarta.validation.Valid;
import org.db_crud.db_crud.models.Aluno;
import org.db_crud.db_crud.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/escola/{id}")
    public ResponseEntity<List<Aluno>> findAllByEscolaId(@PathVariable Integer id){
        List<Aluno> alunos = alunoService.findAllByEscolaId(id);
        return ResponseEntity.ok().body(alunos);
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<Aluno>> findAllByCursoId(@PathVariable Integer id){
        List<Aluno> alunos = alunoService.findAllByCursoId(id);
        return ResponseEntity.ok().body(alunos);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Aluno> criaAluno(@Valid @RequestBody Aluno aluno){
        this.alunoService.create(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{matricula}").buildAndExpand(aluno.getMatricula()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{matricula}")
    @Validated
    public ResponseEntity<?> atualizaAluno(
            @Valid
            @PathVariable Integer matricula,
            @RequestBody Aluno aluno
    ){
        this.alunoService.update(matricula, aluno);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<?> deletaAluno(@PathVariable Integer matricula){
        this.alunoService.delete(matricula);
        return ResponseEntity.noContent().build();
    }
}
