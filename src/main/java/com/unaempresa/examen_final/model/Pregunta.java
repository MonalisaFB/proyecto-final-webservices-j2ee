package com.unaempresa.examen_final.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El enunciado es obligatorio")
    @Size(min = 5, max = 1000, message = "El enunciado debe tener entre {min} y {max} caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @NotNull(message = "El tipo de pregunta es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPregunta tipo;

    @NotBlank(message = "La respuesta correcta es obligatoria")
    @Size(max = 500, message = "La respuesta correcta no puede superar los {max} caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String respuestaCorrecta;

    @Size(max = 1000, message = "Las opciones no pueden superar los {max} caracteres")
    @Column(columnDefinition = "TEXT")
    private String opciones;

    @NotNull(message = "Debe seleccionar una temática")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tematica_id", nullable = false)
    private Tematica tematica;
}