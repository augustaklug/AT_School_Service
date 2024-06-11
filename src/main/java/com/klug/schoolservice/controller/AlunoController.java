package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Aluno;
import com.klug.schoolservice.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        return alunoService.save(aluno);
    }

    @GetMapping
    public List<Aluno> findAll() {
        return alunoService.findAll();
    }
}
