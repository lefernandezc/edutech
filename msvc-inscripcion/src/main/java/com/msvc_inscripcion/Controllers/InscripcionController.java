package com.msvc_inscripcion.Controllers;

import com.msvc_inscripcion.Dtos.InscripcionDTO;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
import com.msvc_inscripcion.Services.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inscripcion")
@Validated
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Inscripcion> save(@RequestBody @Valid Inscripcion inscripcion){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.inscripcionService.save(inscripcion));
    }

    @GetMapping("/inscripcion/{id}")
    public ResponseEntity<List<Inscripcion>> findByIdAlumno(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionService.findByAlumnoId(id));
    }
}
