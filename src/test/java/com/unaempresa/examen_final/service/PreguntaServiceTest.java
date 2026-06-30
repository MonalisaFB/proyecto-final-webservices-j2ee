package com.unaempresa.examen_final.service;

import com.unaempresa.examen_final.exception.PreguntaNoEncontradaException;
import com.unaempresa.examen_final.model.*;
import com.unaempresa.examen_final.repository.PreguntaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PreguntaServiceTest {

    @Mock
    private PreguntaRepository preguntaRepository;

    @InjectMocks
    private PreguntaService preguntaService;

    @Test
    void listarTodas_DeveRetornarPagina() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Pregunta> paginaEsperada = new PageImpl<>(List.of());
        when(preguntaRepository.findAll(pageable)).thenReturn(paginaEsperada);

        // Act
        Page<Pregunta> resultado = preguntaService.listarTodas(pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(paginaEsperada, resultado);
        verify(preguntaRepository).findAll(pageable);
    }

    @Test
    void buscarPorId_QuandoExiste_DeveRetornarPregunta() {
        // Arrange
        Long id = 1L;
        Pregunta pregunta = mock(Pregunta.class);
        when(preguntaRepository.findById(id)).thenReturn(Optional.of(pregunta));

        // Act
        Pregunta resultado = preguntaService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(pregunta, resultado);
        verify(preguntaRepository).findById(id);
    }

    @Test
    void buscarPorId_QuandoNaoExiste_LancaExcecao() {
        // Arrange
        Long id = 999L;
        when(preguntaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PreguntaNoEncontradaException.class, () -> preguntaService.buscarPorId(id));
        verify(preguntaRepository).findById(id);
    }

    @Test
    void guardar_DeveSalvarERetornarPregunta() {
        // Arrange
        Pregunta pregunta = mock(Pregunta.class);
        when(preguntaRepository.save(pregunta)).thenReturn(pregunta);

        // Act
        Pregunta resultado = preguntaService.guardar(pregunta);

        // Assert
        assertNotNull(resultado);
        assertEquals(pregunta, resultado);
        verify(preguntaRepository).save(pregunta);
    }

    @Test
    void eliminar_DeveChamarDeleteNoRepositorio() {
        // Arrange
        Long id = 1L;

        // Act
        preguntaService.eliminar(id);

        // Assert
        verify(preguntaRepository).deleteById(id);
    }

    @Test
    void listarPorTematica_DeveRetornarPagina() {
        // Arrange
        Long tematicaId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Pregunta> paginaEsperada = new PageImpl<>(List.of());
        when(preguntaRepository.findByTematicaId(tematicaId, pageable)).thenReturn(paginaEsperada);

        // Act
        Page<Pregunta> resultado = preguntaService.listarPorTematica(tematicaId, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(paginaEsperada, resultado);
        verify(preguntaRepository).findByTematicaId(tematicaId, pageable);
    }

    @Test
    void filtrar_DeveChamarFiltrarNoRepositorio() {
        // Arrange
        Long tematicaId = 1L;
        TipoPregunta tipo = TipoPregunta.VF;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Pregunta> paginaEsperada = new PageImpl<>(List.of());
        when(preguntaRepository.filtrar(eq(tematicaId), eq(PreguntaVerdaderoFalso.class), eq(pageable)))
                .thenReturn(paginaEsperada);

        // Act
        Page<Pregunta> resultado = preguntaService.filtrar(tematicaId, tipo, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(paginaEsperada, resultado);
        verify(preguntaRepository).filtrar(tematicaId, PreguntaVerdaderoFalso.class, pageable);
    }

    @Test
    void filtrar_QuandoTipoNull_DevePassarNullParaRepositorio() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Pregunta> paginaEsperada = new PageImpl<>(List.of());
        when(preguntaRepository.filtrar(eq(null), eq(null), eq(pageable)))
                .thenReturn(paginaEsperada);

        // Act
        Page<Pregunta> resultado = preguntaService.filtrar(null, null, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(paginaEsperada, resultado);
        verify(preguntaRepository).filtrar(null, null, pageable);
    }
}