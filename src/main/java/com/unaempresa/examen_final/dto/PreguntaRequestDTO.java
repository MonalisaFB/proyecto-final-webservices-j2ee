package com.unaempresa.examen_final.dto;

import com.unaempresa.examen_final.model.TipoPregunta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreguntaRequestDTO {

    private Long id;

    @NotNull(message = "Debe seleccionar un tipo de pregunta")
    private TipoPregunta tipo;

    @NotBlank(message = "El enunciado es obligatorio")
    @Size(min = 5, max = 1000, message = "El enunciado debe tener entre {min} y {max} caracteres")
    private String enunciado;

    @NotNull(message = "Debe seleccionar una temática")
    private Long tematicaId;

    private Boolean respuestaCorrecta;

    private String opcionCorrecta;

    private List<String> opciones;

    private List<String> opcionesCorrectas;
}