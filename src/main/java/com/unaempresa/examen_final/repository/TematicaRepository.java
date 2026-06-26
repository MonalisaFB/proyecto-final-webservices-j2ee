package com.unaempresa.examen_final.repository;

import com.unaempresa.examen_final.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TematicaRepository extends JpaRepository<Tematica, Long> {
    boolean existsByNombre(String nombre);
}