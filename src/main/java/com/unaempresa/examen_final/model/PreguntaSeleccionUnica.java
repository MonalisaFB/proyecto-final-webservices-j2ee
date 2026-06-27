package com.unaempresa.examen_final.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("SELECCION_UNICA")
@Getter 
@Setter
@NoArgsConstructor
public class PreguntaSeleccionUnica extends Pregunta {

    @ElementCollection
    @CollectionTable(name = "opciones_seleccion_unica", joinColumns = @JoinColumn(name = "pregunta_id"))
    @Column(name = "opcion")
    private List<String> opciones = new ArrayList<>();

    @NotBlank(message = "Debe indicar la opción correcta")
    private String opcionCorrecta;

    @Override
    public String getTipoDescripcion() { return "Selección única"; }
}
