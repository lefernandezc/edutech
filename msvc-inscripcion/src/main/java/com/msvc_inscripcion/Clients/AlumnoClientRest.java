package com.msvc_inscripcion.Clients;

import com.msvc_inscripcion.Models.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-alumno", url = "localhost:8001/api/v1/alumno")
public interface AlumnoClientRest {

    @GetMapping
    List<Alumno> findAllByIdAlumno(Long idAlumno);

    @GetMapping("/alumno/{id}")
    Alumno findByIdInscripcion(@PathVariable Long id);


}
