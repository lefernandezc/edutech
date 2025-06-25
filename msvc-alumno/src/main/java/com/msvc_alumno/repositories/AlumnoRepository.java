package com.msvc_alumno.repositories;

import com.msvc_alumno.model.entites.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findByIdCurso(Long idCurso);
}
