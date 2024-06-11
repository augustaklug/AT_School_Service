package com.klug.schoolservice.controller;

import com.klug.schoolservice.model.Aluno;
import com.klug.schoolservice.service.AlunoService;
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

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testSave() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Test");
        aluno.setCpf("123.456.789-00");
        aluno.setEmail("test@example.com");
        aluno.setTelefone("1234567890");
        aluno.setEndereco("Street");

        Mockito.when(alunoService.save(Mockito.any(Aluno.class))).thenReturn(aluno);

        String json = "{\"nome\":\"Test\",\"cpf\":\"123.456.789-00\",\"email\":\"test@example.com\",\"telefone\":\"1234567890\",\"endereco\":\"Street\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/alunos")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"nome\":\"Test\",\"cpf\":\"123.456.789-00\",\"email\":\"test@example.com\",\"telefone\":\"1234567890\",\"endereco\":\"Street\"}"));
    }

    @Test
    @WithMockUser(username = "professor", roles = {"USER"})
    public void testFindAll() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setNome("Test");
        aluno.setCpf("123.456.789-00");
        aluno.setEmail("test@example.com");
        aluno.setTelefone("1234567890");
        aluno.setEndereco("Street");

        Mockito.when(alunoService.findAll()).thenReturn(Collections.singletonList(aluno));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/alunos")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":null,\"nome\":\"Test\",\"cpf\":\"123.456.789-00\",\"email\":\"test@example.com\",\"telefone\":\"1234567890\",\"endereco\":\"Street\"}]"));
    }
}
