package com.msvc_alumno.repositories;

import com.msvc_alumno.model.entites.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
