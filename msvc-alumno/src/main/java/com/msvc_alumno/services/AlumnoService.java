package com.msvc_alumno.services;


import com.msvc_alumno.dtos.InscripcionDTO;
import com.msvc_alumno.dtos.NotasDTO;
import com.msvc_alumno.dtos.ProfesorDTO;
import com.msvc_alumno.model.entites.Alumno;

import java.util.List;

public interface AlumnoService {
    List<Alumno> findAll();
    Alumno findById(Long id);
    Alumno save(Alumno alumno);
    List<InscripcionDTO> findByInscripcionId(Long idAlumno);
    List<NotasDTO> findByNotasId(Long idAlumno);
    List<ProfesorDTO> findByProfesorId(Long idAlumno);
}
