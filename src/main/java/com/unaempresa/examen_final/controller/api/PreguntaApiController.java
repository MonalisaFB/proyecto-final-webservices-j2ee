package com.unaempresa.examen_final.controller.api;

import com.unaempresa.examen_final.dto.PreguntaRequestDTO;
import com.unaempresa.examen_final.dto.PreguntaResponseDTO;
import com.unaempresa.examen_final.mapper.PreguntaMapper;
import com.unaempresa.examen_final.model.*;
import com.unaempresa.examen_final.service.PreguntaService;
import com.unaempresa.examen_final.service.TematicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/preguntas")
@RequiredArgsConstructor
public class PreguntaApiController {

    private final PreguntaService preguntaService;
    private final TematicaService tematicaService;

    @GetMapping
    public ResponseEntity<Page<PreguntaResponseDTO>> listar(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) Long tematicaId,
            @RequestParam(required = false) TipoPregunta tipo) {
        Page<Pregunta> pagina = preguntaService.filtrar(tematicaId, tipo, pageable);
        return ResponseEntity.ok(pagina.map(PreguntaMapper::toResponseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaResponseDTO> obtener(@PathVariable Long id) {
        Pregunta pregunta = preguntaService.buscarPorId(id);
        return ResponseEntity.ok(PreguntaMapper.toResponseDTO(pregunta));
    }

    @PostMapping
    public ResponseEntity<PreguntaResponseDTO> crear(@Valid @RequestBody PreguntaRequestDTO request) {
        Pregunta pregunta = convertirRequestAEntidad(request, null);
        Pregunta guardada = preguntaService.guardar(pregunta);
        return ResponseEntity
                .created(URI.create("/api/preguntas/" + guardada.getId()))
                .body(PreguntaMapper.toResponseDTO(guardada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreguntaResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PreguntaRequestDTO request) {
        preguntaService.buscarPorId(id);
        Pregunta pregunta = convertirRequestAEntidad(request, id);
        Pregunta guardada = preguntaService.guardar(pregunta);
        return ResponseEntity.ok(PreguntaMapper.toResponseDTO(guardada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        preguntaService.buscarPorId(id);
        preguntaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private Pregunta convertirRequestAEntidad(PreguntaRequestDTO dto, Long id) {
        Tematica tematica = tematicaService.buscarPorId(dto.getTematicaId());
        Pregunta pregunta;

        switch (dto.getTipo()) {
            case VF -> {
                PreguntaVerdaderoFalso vf = new PreguntaVerdaderoFalso();
                vf.setRespuestaCorrecta(dto.getRespuestaCorrecta());
                pregunta = vf;
            }
            case UNICA -> {
                PreguntaSeleccionUnica u = new PreguntaSeleccionUnica();
                u.setOpcionCorrecta(dto.getOpcionCorrecta());
                u.setOpciones(dto.getOpciones() != null ? dto.getOpciones() : new java.util.ArrayList<>());
                pregunta = u;
            }
            case MULTIPLE -> {
                PreguntaSeleccionMultiple m = new PreguntaSeleccionMultiple();
                m.setOpciones(dto.getOpciones() != null ? dto.getOpciones() : new java.util.ArrayList<>());
                m.setOpcionesCorrectas(dto.getOpcionesCorrectas() != null ? dto.getOpcionesCorrectas() : new java.util.ArrayList<>());
                pregunta = m;
            }
            default -> throw new IllegalArgumentException("Tipo no válido: " + dto.getTipo());
        }

        if (id != null) {
            pregunta.setId(id);
        }
        pregunta.setEnunciado(dto.getEnunciado().trim());
        pregunta.setTematica(tematica);

        return pregunta;
    }
}