package com.msvc_profesor.services;

import com.msvc_profesor.dtos.AlumnoDTO;
import com.msvc_profesor.dtos.NotasDTO;
import com.msvc_profesor.dtos.ProfesorDTO;
import com.msvc_profesor.models.entilies.Profesor;

import java.util.List;

public interface ProfesorService {

    List<Profesor> findAll();
    Profesor findById(Long id);
    Profesor save(Profesor profesor);

}
