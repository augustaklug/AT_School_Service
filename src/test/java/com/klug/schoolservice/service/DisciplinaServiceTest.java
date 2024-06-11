package com.klug.schoolservice.service;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.repository.DisciplinaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class DisciplinaServiceTest {

    @Autowired
    private DisciplinaService disciplinaService;

    @MockBean
    private DisciplinaRepository disciplinaRepository;

    @Test
    public void testSave() {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Math");
        Mockito.when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplina);
        Disciplina result = disciplinaService.save(disciplina);
        assertEquals("Math", result.getNome());
    }

    @Test
    public void testFindAll() {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Math");
        Mockito.when(disciplinaRepository.findAll()).thenReturn(Collections.singletonList(disciplina));
        List<Disciplina> result = disciplinaService.findAll();
        assertEquals(1, result.size());
        assertEquals("Math", result.get(0).getNome());
    }
}
