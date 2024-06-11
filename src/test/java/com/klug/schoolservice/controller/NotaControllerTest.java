package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Nota;
import com.klug.schoolservice.service.NotaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotaController.class)
public class NotaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotaService notaService;

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testSave() throws Exception {
        Nota nota = new Nota();
        nota.setId(1L);
        nota.setValor(8.0);
        Mockito.when(notaService.save(Mockito.any(Nota.class))).thenReturn(nota);

        String json = "{\"aluno\":{\"id\":1},\"disciplina\":{\"id\":1},\"valor\":8.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/notas")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"aluno\":null,\"disciplina\":null,\"valor\":8.0}"));
    }

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testFindAprovados() throws Exception {
        Nota nota = new Nota();
        nota.setValor(8.0);
        Mockito.when(notaService.findAprovados(1L)).thenReturn(Collections.singletonList(nota));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/notas/aprovados")
                        .param("disciplinaId", "1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"aluno\":null,\"disciplina\":null,\"valor\":8.0}]"));
    }

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testFindReprovados() throws Exception {
        Nota nota = new Nota();
        nota.setValor(6.0);
        Mockito.when(notaService.findReprovados(1L)).thenReturn(Collections.singletonList(nota));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/notas/reprovados")
                        .param("disciplinaId", "1")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"aluno\":null,\"disciplina\":null,\"valor\":6.0}]"));
    }
}
