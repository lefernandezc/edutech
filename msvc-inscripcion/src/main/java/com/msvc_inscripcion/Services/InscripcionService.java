package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Models.Entities.Inscripcion;
import com.msvc_inscripcion.Dtos.InscripcionDTO;

import java.util.List;

public interface InscripcionService {

    List<InscripcionDTO> findAll();
    Inscripcion findById(Long id);
    Inscripcion save(Inscripcion inscripcion);
    List <Inscripcion> findByAlumnoId(Long alumnnoId);

}
