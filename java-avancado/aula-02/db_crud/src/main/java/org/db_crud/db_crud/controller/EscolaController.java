package org.db_crud.db_crud.controller;

import org.db_crud.db_crud.controller.dto.AlunoDTO;
import org.db_crud.db_crud.controller.dto.EscolaDTO;
import org.db_crud.db_crud.controller.form.EscolaForm;
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
@RequestMapping("/escolas/")
public class EscolaController {

    @Autowired
    private EscolaRepository escolaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<EscolaDTO>> retornaEscolas(){

        List<Escola> listaEscolas = (ArrayList<Escola>) escolaRepository.findAll();
        List<EscolaDTO> listaEscolasDTO = new ArrayList<EscolaDTO>();

        for (Escola escola : listaEscolas){

            EscolaDTO escolaDTO = new EscolaDTO(escola);
            listaEscolasDTO.add(escolaDTO);

        }

        return ResponseEntity.ok(listaEscolasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornaEscolaPorId(@PathVariable Integer id){
        if (id == null){

            return ResponseEntity.badRequest().build();

        }

        try {

            Escola escola = escolaRepository.getReferenceById(id);
            EscolaDTO escolaDTO = new EscolaDTO(escola);
            return ResponseEntity.ok(escolaDTO);

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping
    public ResponseEntity<EscolaDTO> insereEscola(
            @RequestBody EscolaForm escolaForm,
            UriComponentsBuilder uriComponentsBuilder
            ){

        Escola escola = escolaForm.criaEscola();
        escolaRepository.save(escola);
        EscolaDTO escolaDTO = new EscolaDTO(escola);
        uriComponentsBuilder.path("/escolas/{id}");
        URI uri = uriComponentsBuilder.buildAndExpand(escola.getId()).toUri();

        return ResponseEntity.created(uri).body(escolaDTO);
    }

    @PostMapping("/{escolaId}/atribui/{alunoId}")
    public ResponseEntity<?> atribuiEscolaEmAluno(
            @PathVariable Integer escolaId,
            @PathVariable Integer alunoId
    ){

        if (alunoId == null || escolaId == null){

            return ResponseEntity.badRequest().build();

        }

        try{

            Escola escola = escolaRepository.getReferenceById(escolaId);
            Aluno aluno = alunoRepository.getReferenceById(alunoId);

            aluno.setEscola(escola);
            alunoRepository.save(aluno);

            return ResponseEntity.ok().build();

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaEscola(
            @PathVariable Integer id,
            @RequestBody EscolaForm escolaForm
    ){

        if (id == null){

            ResponseEntity.badRequest().build();

        }

        try {

            Escola escola = escolaRepository.getReferenceById(id);
            escola.setNome(escolaForm.getNome());
            escola.setLocalizacao(escolaForm.getLocalizacao());
            escolaRepository.save(escola);
            EscolaDTO escolaDTO = new EscolaDTO(escola);

            return ResponseEntity.ok(escolaDTO);

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaEscola(@PathVariable Integer id){

        if (id == null){

            return ResponseEntity.badRequest().build();

        }

        try {

            Escola escola = escolaRepository.getReferenceById(id);
            EscolaDTO escolaDTO = new EscolaDTO(escola);
            escolaRepository.delete(escola);

            return ResponseEntity.ok(escolaDTO);

        } catch (Exception e){

            return ResponseEntity.notFound().build();

        }
    }
}
