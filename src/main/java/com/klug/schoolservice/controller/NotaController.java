package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Nota;
import com.klug.schoolservice.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {
    @Autowired
    private NotaService notaService;

    @PostMapping
    public Nota save(@RequestBody Nota nota) {
        return notaService.save(nota);
    }

    @GetMapping("/aprovados")
    public List<Nota> findAprovados(@RequestParam Long disciplinaId) {
        return notaService.findAprovados(disciplinaId);
    }

    @GetMapping("/reprovados")
    public List<Nota> findReprovados(@RequestParam Long disciplinaId) {
        return notaService.findReprovados(disciplinaId);
    }
}
