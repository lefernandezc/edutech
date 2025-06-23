package com.msvc_notas.Services;

import com.msvc_notas.Models.Entities.Notas;

import java.util.List;

public interface NotasService {

    List<Notas> findAll();
    Notas findById(Long id);
    Notas save (Notas notas);
    List <Notas> findByAlumnoId(Long alumnoId);
    List <Notas> findByProfesorId(Long profesorId);
}
