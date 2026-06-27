package com.unaempresa.examen_final.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pregunta", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El enunciado es obligatorio")
    @Size(min = 5, max = 1000, message = "El enunciado debe tener entre {min} y {max} caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @NotNull(message = "Debe seleccionar una temática")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tematica_id", nullable = false)
    private Tematica tematica;

    public abstract String getTipoDescripcion();
}