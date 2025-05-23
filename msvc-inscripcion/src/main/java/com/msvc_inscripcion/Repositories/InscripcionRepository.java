package com.msvc_inscripcion.Repositories;

import com.msvc_inscripcion.Models.Entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {

    List<Inscripcion> findByIdAlumno(Long idAlumno);
}
