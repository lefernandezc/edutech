package com.msvc_inscripcion.Repositories;

import com.msvc_inscripcion.Models.Entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepositories extends JpaRepository<Inscripcion,Long> {
}
