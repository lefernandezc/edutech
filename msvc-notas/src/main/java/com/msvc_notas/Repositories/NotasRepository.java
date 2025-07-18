package com.msvc_notas.Repositories;

import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Models.Entities.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotasRepository extends JpaRepository<Notas, Long> {
    List<NotasDTO> findByIdAlumno(Long idAlumno);
    List<NotasDTO> findByIdCurso(Long idProfesor);
}
