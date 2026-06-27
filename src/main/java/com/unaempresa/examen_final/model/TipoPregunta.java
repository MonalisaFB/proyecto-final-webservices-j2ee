package com.unaempresa.examen_final.model;

public enum TipoPregunta {
    VF(PreguntaVerdaderoFalso.class),
    UNICA(PreguntaSeleccionUnica.class),
    MULTIPLE(PreguntaSeleccionMultiple.class);

    private final Class<? extends Pregunta> entityClass;

    TipoPregunta(Class<? extends Pregunta> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<? extends Pregunta> getEntityClass() {
        return entityClass;
    }
}