package com.msvc_profesor.controllers;

import com.msvc_profesor.dtos.ProfesorDTO;
import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.services.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesor")
@Validated
public class ProfesorControllers {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.profesorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.profesorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Profesor> save(@RequestBody @Valid Profesor profesor){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.profesorService.save(profesor));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<List<Profesor>> findByIdAlumno(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.profesorService.findByAlumnoId(id));
    }

    @GetMapping("/notas/{id}")
    public ResponseEntity<List<Profesor>> findByIdNotas(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.profesorService.findByNotasId(id));
    }

}
