package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Nota;
import com.klug.schoolservice.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody Nota nota) {
        Nota savedNota = notaService.save(nota);
        return new ResponseEntity<>(savedNota, HttpStatus.CREATED);
    }

    @GetMapping("/aprovados")
    public ResponseEntity<List<Nota>> findAprovados(@RequestParam Long disciplinaId) {
        List<Nota> aprovados = notaService.findAprovados(disciplinaId);
        if (aprovados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aprovados, HttpStatus.OK);
    }

    @GetMapping("/reprovados")
    public ResponseEntity<List<Nota>> findReprovados(@RequestParam Long disciplinaId) {
        List<Nota> reprovados = notaService.findReprovados(disciplinaId);
        if (reprovados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reprovados, HttpStatus.OK);
    }
}
