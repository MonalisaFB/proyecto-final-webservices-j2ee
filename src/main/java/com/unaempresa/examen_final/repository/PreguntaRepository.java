package com.unaempresa.examen_final.repository;

import com.unaempresa.examen_final.model.Pregunta;
import com.unaempresa.examen_final.model.Tematica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    Page<Pregunta> findByTematica(Tematica tematica, Pageable pageable);
    Page<Pregunta> findByTematicaId(Long tematicaId, Pageable pageable);
    long countByTematicaId(Long tematicaId);
}