package com.msvc_notas.Services;

import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Models.Entities.Notas;

import java.util.List;

public interface NotasService {

    List<Notas> findAll();
    Notas findById(Long id);
    Notas save (Notas notas);
    List <NotasDTO> findByAlumnoId(Long alumnoId);
    List <NotasDTO> findByProfesorId(Long profesorId);
}
