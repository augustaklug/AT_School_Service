package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public Disciplina save(@RequestBody Disciplina disciplina) {
        return disciplinaService.save(disciplina);
    }

    @GetMapping
    public List<Disciplina> findAll() {
        return disciplinaService.findAll();
    }
}
