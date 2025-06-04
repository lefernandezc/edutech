package com.msvc_inscripcion.Repositories;

import com.msvc_inscripcion.Models.Entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    Optional<Inscripcion> findByInscripcion (Long inscripcion);
}
