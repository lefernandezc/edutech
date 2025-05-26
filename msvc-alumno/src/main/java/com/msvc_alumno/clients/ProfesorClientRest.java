package com.msvc_alumno.clients;

import com.msvc_alumno.model.Notas;
import com.msvc_alumno.model.Profesor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-profesor", url = "localhost:8004/api/v1/profesor")
public interface ProfesorClientRest {

    @GetMapping
    List<Profesor> findAllByIdProfesor(Long idProfesor);

    @GetMapping("/{id}")
    Profesor findByIdAlumno(@PathVariable Long id);

}
