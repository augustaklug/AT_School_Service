package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Aluno;
import com.klug.schoolservice.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        Aluno savedAluno = alunoService.save(aluno);
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        List<Aluno> alunos = alunoService.findAll();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
}
