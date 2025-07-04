package com.msvc_inscripcion.Repositories;

import com.msvc_inscripcion.Models.Entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByIdInscripcion (Long id);
}
