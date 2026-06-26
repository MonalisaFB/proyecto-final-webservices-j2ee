package com.unaempresa.examen_final.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "preguntas")
@ToString(exclude = "preguntas")
public class Tematica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la temática es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre {min} y {max} caracteres")
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "tematica", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pregunta> preguntas = new ArrayList<>();

    public Tematica(String nombre) {
        this.nombre = nombre;
    }
}