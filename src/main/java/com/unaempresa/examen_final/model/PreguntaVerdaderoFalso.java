package com.unaempresa.examen_final.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("VERDADERO_FALSO")
@Getter 
@Setter 
@NoArgsConstructor
public class PreguntaVerdaderoFalso extends Pregunta {

    @NotNull(message = "Debe indicar si la respuesta correcta es verdadero o falso")
    @Column(name = "respuesta_vf")
    private Boolean respuestaCorrecta;

    @Override
    public String getTipoDescripcion() { return "Verdadero/Falso"; }
}
