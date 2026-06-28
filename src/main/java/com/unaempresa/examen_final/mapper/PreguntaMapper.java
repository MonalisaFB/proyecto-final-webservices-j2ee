package com.unaempresa.examen_final.mapper;

import com.unaempresa.examen_final.dto.PreguntaResponseDTO;
import com.unaempresa.examen_final.model.*;

public class PreguntaMapper {

    public static PreguntaResponseDTO toResponseDTO(Pregunta pregunta) {
        PreguntaResponseDTO.PreguntaResponseDTOBuilder builder = PreguntaResponseDTO.builder()
            .id(pregunta.getId())
            .enunciado(pregunta.getEnunciado())
            .tipo(obtenerTipo(pregunta))
            .tipoDescripcion(pregunta.getTipoDescripcion())
            .tematicaId(pregunta.getTematica().getId())
            .tematicaNombre(pregunta.getTematica().getNombre());

        switch (obtenerTipo(pregunta)) {
            case VF -> {
                PreguntaVerdaderoFalso vf = (PreguntaVerdaderoFalso) pregunta;
                builder.respuestaCorrecta(vf.getRespuestaCorrecta());
            }
            case UNICA -> {
                PreguntaSeleccionUnica u = (PreguntaSeleccionUnica) pregunta;
                builder.opcionCorrecta(u.getOpcionCorrecta());
                builder.opciones(u.getOpciones());
            }
            case MULTIPLE -> {
                PreguntaSeleccionMultiple m = (PreguntaSeleccionMultiple) pregunta;
                builder.opciones(m.getOpciones());
                builder.opcionesCorrectas(m.getOpcionesCorrectas());
            }
        }

        return builder.build();
    }

    private static TipoPregunta obtenerTipo(Pregunta pregunta) {
        if (pregunta instanceof PreguntaVerdaderoFalso) return TipoPregunta.VF;
        if (pregunta instanceof PreguntaSeleccionUnica) return TipoPregunta.UNICA;
        if (pregunta instanceof PreguntaSeleccionMultiple) return TipoPregunta.MULTIPLE;
        throw new IllegalArgumentException("Tipo de pregunta desconocido: " + pregunta.getClass());
    }
}