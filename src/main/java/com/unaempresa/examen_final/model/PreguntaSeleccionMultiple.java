package com.unaempresa.examen_final.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("SELECCION_MULTIPLE")

public class PreguntaSeleccionMultiple extends Pregunta {

    @ElementCollection
    @CollectionTable(name = "opciones_seleccion_multiple", joinColumns = @JoinColumn(name = "pregunta_id"))
    @Column(name = "opcion")
    private List<String> opciones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "respuestas_correctas_multiple", joinColumns = @JoinColumn(name = "pregunta_id"))
    @Column(name = "respuesta")
    private List<String> opcionesCorrectas = new ArrayList<>();

    @Override
    public String getTipoDescripcion() { return "Selección múltiple"; }
}
