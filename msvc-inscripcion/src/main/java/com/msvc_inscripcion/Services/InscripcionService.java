package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Models.Entities.Inscripcion;

import java.util.List;

public interface InscripcionService {

    List<Inscripcion> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    List <InscripcionAlumnoDTO> findInscripcion

}
