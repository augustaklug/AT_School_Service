package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Disciplina;
import com.klug.schoolservice.service.DisciplinaService;
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

@WebMvcTest(DisciplinaController.class)
public class DisciplinaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisciplinaService disciplinaService;

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testSave() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L);
        disciplina.setNome("Math");
        disciplina.setCodigo("MTH101"); // Certifique-se de definir o código

        Mockito.when(disciplinaService.save(Mockito.any(Disciplina.class))).thenReturn(disciplina);

        String json = "{\"nome\":\"Math\",\"codigo\":\"MTH101\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/disciplinas")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"id\":1,\"nome\":\"Math\",\"codigo\":\"MTH101\"}"));
    }

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testFindAll() throws Exception {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(1L); // Definir ID para correspondência com a resposta esperada
        disciplina.setNome("Math");
        disciplina.setCodigo("MTH101"); // Definir código para correspondência com a resposta esperada

        Mockito.when(disciplinaService.findAll()).thenReturn(Collections.singletonList(disciplina));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/disciplinas")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Adicionar token CSRF
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nome\":\"Math\",\"codigo\":\"MTH101\"}]"));
    }
}
