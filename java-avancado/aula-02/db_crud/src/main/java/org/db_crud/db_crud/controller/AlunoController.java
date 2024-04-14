package org.db_crud.db_crud.controller;

import org.db_crud.db_crud.controller.dto.AlunoDTO;
import org.db_crud.db_crud.controller.form.AlunoForm;
import org.db_crud.db_crud.model.Aluno;
import org.db_crud.db_crud.model.Escola;
import org.db_crud.db_crud.repository.AlunoRepository;
import org.db_crud.db_crud.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alunos/")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private EscolaRepository escolaRepository;

    @GetMapping
    public ResponseEntity<?> retornaAlunos(){

        List<Aluno> listaAluno = (ArrayList<Aluno>) alunoRepository.findAll();
        List<AlunoDTO> listaAlunoDTO = new ArrayList<AlunoDTO>();
        for (Aluno aluno : listaAluno){

            AlunoDTO alunoDTO = new AlunoDTO(aluno);
            listaAlunoDTO.add(alunoDTO);

        }

        return ResponseEntity.ok(listaAlunoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaAlunosId(@PathVariable Integer id){

        if (id == null){

            return ResponseEntity.badRequest().build();

        }

        try {

            Aluno aluno = alunoRepository.getReferenceById(id);
            AlunoDTO alunoDTO = new AlunoDTO(aluno);
            return ResponseEntity.ok(alunoDTO);

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping
    public ResponseEntity<AlunoDTO> inserir(
            @RequestBody AlunoForm AF,
            UriComponentsBuilder UCB
            ){

        Aluno aluno = AF.criaAluno();
        alunoRepository.save(aluno);
        AlunoDTO alunoDTO = new AlunoDTO(aluno);
        UCB.path("/alunos/{id}");
        URI uri = UCB.buildAndExpand(aluno.getMatricula()).toUri();

        return ResponseEntity.created(uri).body(alunoDTO);
    }

    @PostMapping("/{alunoId}/atribui/{escolaId}")
    public ResponseEntity<?> atribuiAlunoEmEscola(
            @PathVariable Integer alunoId,
            @PathVariable Integer escolaId
    ){

        if (alunoId == null || escolaId == null){

            return ResponseEntity.badRequest().build();

        }

        try{

            Escola escola = escolaRepository.getReferenceById(escolaId);
            Aluno aluno = alunoRepository.getReferenceById(alunoId);

            escola.setAlunos(aluno);
            escolaRepository.save(escola);

            return ResponseEntity.ok().build();

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAluno(
            @PathVariable Integer id,
            @RequestBody AlunoForm AF
    ){

        if (id == null){

            ResponseEntity.badRequest().build();

        }

        try {

            Aluno aluno = alunoRepository.getReferenceById(id);
            aluno.setNome(AF.getNome());
            aluno.setCpf(AF.getCpf());
            aluno.setCurso(AF.getCurso());
            alunoRepository.save(aluno);
            AlunoDTO alunoDTO = new AlunoDTO(aluno);

            return ResponseEntity.ok(alunoDTO);

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaAluno(@PathVariable Integer id){

        if (id == null){

            return ResponseEntity.badRequest().build();

        }

        try {

            Aluno aluno = alunoRepository.getReferenceById(id);
            AlunoDTO alunoDTO = new AlunoDTO(aluno);
            alunoRepository.delete(aluno);

            return ResponseEntity.ok(alunoDTO);

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }

    }

}
