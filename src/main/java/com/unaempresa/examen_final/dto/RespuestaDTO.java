package com.unaempresa.examen_final.dto;

import lombok.Data;
import java.util.List;

@Data
public class RespuestaDTO {
    private Long preguntaId;
    private String respuesta;
    private List<String> respuestas;
}
