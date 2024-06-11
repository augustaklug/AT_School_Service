package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplina> save(@RequestBody Disciplina disciplina) {
        Disciplina savedDisciplina = disciplinaService.save(disciplina);
        return new ResponseEntity<>(savedDisciplina, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        List<Disciplina> disciplinas = disciplinaService.findAll();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }
}
