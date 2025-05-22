package com.msvc_profesor.services;

import com.msvc_profesor.dtos.ProfesorDTO;
import com.msvc_profesor.models.entilies.Profesor;

import java.util.List;

public interface ProfesorService {

    List<ProfesorDTO> findAll();
    Profesor findById(Long id);
    Profesor save(Profesor profesor);
    List<Profesor> findByAlumnoId(Long alumnoid);
    List<Profesor> findByNotasId(Long notasid);
}
