package com.msvc_profesor.clients;

import com.msvc_profesor.models.Notas;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-notas", url = "localhost:8003/api/v1/notas")
public interface NotasClientRest {

    @GetMapping
    List<Notas> findAllByIdNotas(Long idNotas);

    @GetMapping("/{id}")
    Notas findByIdProfesor(@PathVariable Long id);
}
