package com.msvc_notas.Controllers;


import com.msvc_notas.Dto.AlumnoDTO;
import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Dto.ProfesorDTO;
import com.msvc_notas.Models.Entities.Notas;
import com.msvc_notas.Services.NotasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notas")
@Validated
public class NotasController {

    @Autowired
    private NotasService notasService;

    @GetMapping
    public ResponseEntity<List<NotasDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(notasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notas> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(notasService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Notas> save(@Valid @RequestBody Notas notas){
        return ResponseEntity.status(HttpStatus.CREATED).body(notasService.save(notas));
    }

    @GetMapping("/{id}/alumno")
    public ResponseEntity<List<Notas>> findByAlumnoId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.notasService.findByAlumnoId(id));
    }

    @GetMapping("/{id}/profesor")
    public ResponseEntity<List<Notas>> findByProfesorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.notasService.findByProfesorId(id));
    }
}
