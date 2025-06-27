package com.msvc_alumno.controllers;

import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.services.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/alumno")
@Validated

public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.alumnoService.findAll());
    }

    @GetMapping("/{id}")
            public ResponseEntity<Alumno> findById(@PathVariable Long id){
              return ResponseEntity.status(HttpStatus.OK).body(this.alumnoService.findById(id));

        }

    @PostMapping
    public ResponseEntity<Alumno> save(@RequestBody @Valid Alumno alumno) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.alumnoService.save(alumno));
        }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<Alumno>> findByIdCurso(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.alumnoService.findByCursoId(id));
    }


}
