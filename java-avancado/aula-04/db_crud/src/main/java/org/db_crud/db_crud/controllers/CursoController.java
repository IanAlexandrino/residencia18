package org.db_crud.db_crud.controllers;

import org.db_crud.db_crud.models.Curso;
import org.db_crud.db_crud.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@Validated
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/{id}")
    public ResponseEntity<Curso> retornaCursoPorId(@PathVariable Integer id){
        Curso curso = cursoService.findById(id);
        return ResponseEntity.ok().body(curso);
    }

    @GetMapping("/escola/{id}")
    public ResponseEntity<List<Curso>> findAllByEscolaId(@PathVariable Integer id){
        List<Curso> cursos = cursoService.findAllByEscolaId(id);
        return ResponseEntity.ok().body(cursos);
    }

    @PostMapping
    public ResponseEntity<Void> criaCurso(@RequestBody Curso curso){
        this.cursoService.create(curso);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizaCurso(
            @PathVariable Integer id,
            @RequestBody Curso curso
    ){
        curso.setId(id);
        this.cursoService.update(curso);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCurso(@PathVariable Integer id){
        this.cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
