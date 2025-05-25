package com.msvc_inscripcion.Clients;

import com.msvc_inscripcion.Models.Alumno;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-alumno", url = "localhost:8001/api/v1/alumno")
public interface AlumnoClientRest {

    @GetMapping("/alumno/{id}")
    Alumno findById(@PathVariable Long id);
}
