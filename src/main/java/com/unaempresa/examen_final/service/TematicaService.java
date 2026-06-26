package com.unaempresa.examen_final.service;

import com.unaempresa.examen_final.model.Tematica;
import com.unaempresa.examen_final.repository.TematicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TematicaService {

    private final TematicaRepository tematicaRepository;

    @Transactional(readOnly = true)
    public List<Tematica> listarTodas() {
        return tematicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tematica buscarPorId(Long id) {
        return tematicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Temática no encontrada con id: " + id));
    }

    @Transactional
    public Tematica guardar(Tematica tematica) {
        return tematicaRepository.save(tematica);
    }

    @Transactional
    public void eliminar(Long id) {
        tematicaRepository.deleteById(id);
    }
}