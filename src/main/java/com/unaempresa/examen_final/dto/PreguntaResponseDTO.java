package com.unaempresa.examen_final.dto;

import com.unaempresa.examen_final.model.TipoPregunta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreguntaResponseDTO {

    private Long id;
    private String enunciado;
    private TipoPregunta tipo;
    private String tipoDescripcion;
    private Long tematicaId;
    private String tematicaNombre;

    private Boolean respuestaCorrecta;
    private String opcionCorrecta;
    private List<String> opciones;
    private List<String> opcionesCorrectas;
}