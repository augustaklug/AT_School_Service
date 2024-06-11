package com.klug.schoolservice.service;

import com.klug.schoolservice.model.Aluno;
import com.klug.schoolservice.repository.AlunoRepository;
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
public class AlunoServiceTest {

    @Autowired
    private AlunoService alunoService;

    @MockBean
    private AlunoRepository alunoRepository;

    @Test
    public void testSave() {
        Aluno aluno = new Aluno();
        aluno.setNome("Test");
        Mockito.when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        Aluno result = alunoService.save(aluno);
        assertEquals("Test", result.getNome());
    }

    @Test
    public void testFindAll() {
        Aluno aluno = new Aluno();
        aluno.setNome("Test");
        Mockito.when(alunoRepository.findAll()).thenReturn(Collections.singletonList(aluno));
        List<Aluno> result = alunoService.findAll();
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getNome());
    }
}
