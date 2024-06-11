package com.klug.schoolservice.service;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.model.Nota;
import com.klug.schoolservice.repository.DisciplinaRepository;
import com.klug.schoolservice.repository.NotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class NotaServiceTest {

    @Autowired
    private NotaService notaService;

    @MockBean
    private NotaRepository notaRepository;

    @MockBean
    private DisciplinaRepository disciplinaRepository;

    private Disciplina disciplina;
    private Nota nota;

    @BeforeEach
    public void setup() {
        disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setNome("Math");
        disciplina.setCodigo("MTH101");

        nota = new Nota();
        nota.setDisciplina(disciplina);
        nota.setValor(8.0);

        Mockito.when(disciplinaRepository.findById(1L)).thenReturn(Optional.of(disciplina));
        Mockito.when(disciplinaRepository.findById(2L)).thenReturn(Optional.empty());
    }

    @Test
    public void testSave() {
        Mockito.when(notaRepository.save(any(Nota.class))).thenReturn(nota);
        Nota result = notaService.save(nota);
        assertEquals(8.0, result.getValor());
    }

    @Test
    public void testFindAprovados() {
        Mockito.when(notaRepository.findByDisciplinaAndValorGreaterThanEqual(any(Disciplina.class), anyDouble()))
                .thenReturn(Collections.singletonList(nota));
        List<Nota> result = notaService.findAprovados(1L);
        assertEquals(1, result.size());
        assertEquals(8.0, result.get(0).getValor());
    }

    @Test
    public void testFindReprovados() {
        nota.setValor(6.0);
        Mockito.when(notaRepository.findByDisciplinaAndValorLessThan(any(Disciplina.class), anyDouble()))
                .thenReturn(Collections.singletonList(nota));
        List<Nota> result = notaService.findReprovados(1L);
        assertEquals(1, result.size());
        assertEquals(6.0, result.get(0).getValor());
    }

    @Test
    public void testFindAprovadosDisciplinaNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notaService.findAprovados(2L);
        });

        String expectedMessage = "Disciplina não encontrada: 2";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testFindReprovadosDisciplinaNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notaService.findReprovados(2L);
        });

        String expectedMessage = "Disciplina não encontrada: 2";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
