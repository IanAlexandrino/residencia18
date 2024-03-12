package org.db_crud.db_crud.controller;

import org.db_crud.db_crud.controller.dto.AlunoDTO;
import org.db_crud.db_crud.model.Aluno;
import org.db_crud.db_crud.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno/")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<AlunoDTO> retornaAlunos(String nome){

        List<Aluno> listaAluno = (ArrayList<Aluno>) alunoRepository.findAll();
        List<AlunoDTO> listaAlunoDTO = new ArrayList<AlunoDTO>();
        for (Aluno aluno : listaAluno){

            AlunoDTO alunoDTO = new AlunoDTO(aluno);
            listaAlunoDTO.add(alunoDTO);

        }

        return listaAlunoDTO;
    }

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

}
