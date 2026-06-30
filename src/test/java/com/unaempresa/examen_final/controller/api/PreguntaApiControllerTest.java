package com.unaempresa.examen_final.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unaempresa.examen_final.dto.PreguntaRequestDTO;
import com.unaempresa.examen_final.exception.PreguntaNoEncontradaException;
import com.unaempresa.examen_final.model.*;
import com.unaempresa.examen_final.service.PreguntaService;
import com.unaempresa.examen_final.service.TematicaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PreguntaApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockitoBean
    private PreguntaService preguntaService;

    @MockitoBean
    private TematicaService tematicaService;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void listar_DeveRetornar200() throws Exception {
        // Arrange
        when(preguntaService.filtrar(any(), any(), any(Pageable.class)))
                .thenReturn(Page.empty());

        // Act & Assert
        mockMvc.perform(get("/api/preguntas")
                        .with(user("user@email.com").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void obter_QuandoExiste_DeveRetornar200() throws Exception {
        // Arrange
        Tematica tematica = new Tematica(1L, "Teste", List.of());
        PreguntaVerdaderoFalso pregunta = new PreguntaVerdaderoFalso();
        pregunta.setId(1L);
        pregunta.setEnunciado("Brasil fica na América do Sul?");
        pregunta.setRespuestaCorrecta(true);
        pregunta.setTematica(tematica);
        when(preguntaService.buscarPorId(1L)).thenReturn(pregunta);

        // Act & Assert
        mockMvc.perform(get("/api/preguntas/1")
                        .with(user("user@email.com").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.enunciado").value("Brasil fica na América do Sul?"))
                .andExpect(jsonPath("$.tipo").value("VF"))
                .andExpect(jsonPath("$.tipoDescripcion").value("Verdadero/Falso"))
                .andExpect(jsonPath("$.respuestaCorrecta").value(true));
    }

    @Test
    void obter_QuandoNaoExiste_DeveRetornar404() throws Exception {
        // Arrange
        when(preguntaService.buscarPorId(999L))
                .thenThrow(new PreguntaNoEncontradaException(999L));

        // Act & Assert
        mockMvc.perform(get("/api/preguntas/999")
                        .with(user("user@email.com").roles("USER")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Pregunta no encontrada con id: 999"));
    }

    @Test
    void crear_DeveRetornar201() throws Exception {
        // Arrange
        PreguntaRequestDTO request = new PreguntaRequestDTO();
        request.setTipo(TipoPregunta.VF);
        request.setEnunciado("Brasil fica na América do Sul?");
        request.setTematicaId(1L);
        request.setRespuestaCorrecta(true);

        Tematica tematica = new Tematica(1L, "Teste", List.of());
        when(tematicaService.buscarPorId(1L)).thenReturn(tematica);

        PreguntaVerdaderoFalso pregunta = new PreguntaVerdaderoFalso();
        pregunta.setId(1L);
        pregunta.setEnunciado("Brasil fica na América do Sul?");
        pregunta.setRespuestaCorrecta(true);
        pregunta.setTematica(tematica);
        when(preguntaService.guardar(any(Pregunta.class))).thenReturn(pregunta);

        // Act & Assert
        mockMvc.perform(post("/api/preguntas")
                        .with(user("admin@email.com").roles("ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.enunciado").value("Brasil fica na América do Sul?"))
                .andExpect(jsonPath("$.respuestaCorrecta").value(true));
    }

    @Test
    void eliminar_DeveRetornar204() throws Exception {
        // Arrange
        when(preguntaService.buscarPorId(1L)).thenReturn(mock(Pregunta.class));

        // Act & Assert
        mockMvc.perform(delete("/api/preguntas/1")
                        .with(user("admin@email.com").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isNoContent());

        verify(preguntaService).eliminar(1L);
    }
}