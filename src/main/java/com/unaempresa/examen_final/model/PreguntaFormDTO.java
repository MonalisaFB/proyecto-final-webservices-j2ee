package com.unaempresa.examen_final.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PreguntaFormDTO {

    private Long id;

    @NotNull(message = "Debe seleccionar un tipo de pregunta")
    private TipoPregunta tipo;

    @NotBlank(message = "El enunciado es obligatorio")
    @Size(min = 5, max = 1000, message = "El enunciado debe tener entre {min} y {max} caracteres")
    private String enunciado;

    @NotNull(message = "Debe seleccionar una temática")
    private Long tematicaId;

    private String respuestaCorrecta;

    private String opciones;
}