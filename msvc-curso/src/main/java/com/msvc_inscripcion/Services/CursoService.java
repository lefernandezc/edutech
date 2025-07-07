package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Dtos.CursoDTO;
import com.msvc_inscripcion.Models.Entities.Curso;

import java.util.List;

public interface CursoService {

    List<CursoDTO> findAll();
    Curso findById(Long id);
    Curso save(Curso curso);
    List <CursoDTO> findByProfesorId(Long profesorId);


}