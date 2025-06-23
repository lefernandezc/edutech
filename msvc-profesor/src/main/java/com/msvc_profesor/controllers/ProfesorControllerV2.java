package com.msvc_profesor.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/profesor")
@Validated
@Tag(
        name = "Medico API HATEOAS",
        description = "Aquí se generar todos los métodos CRUD para medico"
)
public class ProfesorControllerV2 {
}
