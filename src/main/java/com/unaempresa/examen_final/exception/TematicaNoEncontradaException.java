package com.unaempresa.examen_final.exception;

public class TematicaNoEncontradaException extends RuntimeException {
    public TematicaNoEncontradaException(Long id) {
        super("Temática no encontrada con id: " + id);
    }
}