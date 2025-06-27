package com.msvc_alumno.services;


import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.entites.Alumno;

import java.util.List;

public interface AlumnoService {
    List<Alumno> findAll();
    Alumno findById(Long id);
    Alumno save(Alumno alumno);
    List<Alumno> findByCursoId(Long CursoId);

}
