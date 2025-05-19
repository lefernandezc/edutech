package com.msvc_alumno.clients;

import com.msvc_alumno.model.Notas;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-notas", url = "localhost:8003/api/v1/notas")
public interface NotasClientRest {
    @GetMapping("/{id}")
    Notas findById(@PathVariable Long id);
}
