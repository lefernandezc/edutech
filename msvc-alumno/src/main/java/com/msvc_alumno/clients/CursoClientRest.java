package com.msvc_alumno.clients;

import com.msvc_alumno.model.Curso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-inscripcion", url = "localhost:8002/api/v1/inscripcion")
public interface CursoClientRest {


    @GetMapping("/{id}")
    Curso findById(@PathVariable Long id);
}
