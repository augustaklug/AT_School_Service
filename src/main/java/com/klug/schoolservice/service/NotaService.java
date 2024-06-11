package com.klug.schoolservice.service;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.model.Nota;
import com.klug.schoolservice.repository.DisciplinaRepository;
import com.klug.schoolservice.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Nota save(Nota nota) {
        return notaRepository.save(nota);
    }

    public List<Nota> findAprovados(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada: " + disciplinaId));
        return notaRepository.findByDisciplinaAndValorGreaterThanEqual(disciplina, 7.0);
    }

    public List<Nota> findReprovados(Long disciplinaId) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada: " + disciplinaId));
        return notaRepository.findByDisciplinaAndValorLessThan(disciplina, 7.0);
    }
}
