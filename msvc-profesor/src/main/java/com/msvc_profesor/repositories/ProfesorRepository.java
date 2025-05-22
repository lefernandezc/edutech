package com.msvc_profesor.repositories;

import com.msvc_profesor.models.entilies.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    List<Profesor> findByIdAlumno(Long idAlumno);

    List<Profesor> findByIdNotas(Long idNotas);
}
