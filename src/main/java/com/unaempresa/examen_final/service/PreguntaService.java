package com.unaempresa.examen_final.service;

import com.unaempresa.examen_final.model.Pregunta;
import com.unaempresa.examen_final.repository.PreguntaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;

    @Transactional(readOnly = true)
    public Page<Pregunta> listarTodas(Pageable pageable) {
        return preguntaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Pregunta> listarPorTematica(Long tematicaId, Pageable pageable) {
        return preguntaRepository.findByTematicaId(tematicaId, pageable);
    }

    @Transactional(readOnly = true)
    public Pregunta buscarPorId(Long id) {
        return preguntaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con id: " + id));
    }

    @Transactional
    public Pregunta guardar(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Transactional
    public void eliminar(Long id) {
        preguntaRepository.deleteById(id);
    }
}