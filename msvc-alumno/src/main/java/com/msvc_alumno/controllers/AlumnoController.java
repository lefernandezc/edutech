package com.msvc_alumno.controllers;

import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.repositories.AlumnoRepository;
import com.msvc_alumno.services.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alumno")
@Validated

public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    @Operation(
            summary = "Endpoint que obtiene todos lo de Alumno",
            description = "Este endpoint devuelve un listadob de todo lo de Alumno que se encuentre"+
                    "en la base de datos"
    )
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extracion de Alumno exitosa"
            )
    })
    public ResponseEntity<List<Alumno>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.alumnoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint que devuelve un Alumno por id",
            description = "Endpoint que va devolver un alumno.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion por id correcto"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el Alumno con cierto id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)

                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - Entidad alumno",
                    required = true
            )
    })
    public ResponseEntity<Alumno> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.alumnoService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Endpoint guardar de un alumno",
            description = "Endpoint que me permite capturar un elemento Alumno.class y lo guarde"+
                    "dentro de base de datos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creacion exitosas"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Algun elemento de un msvc no se encuentra",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El elemento que intenta crear ya existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Estructura de datos que permite realizar la creacion de profesor",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Alumno.class)
            )
    )
    public ResponseEntity<Alumno> create(@Valid @RequestBody Alumno alumno){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.alumnoService.save(alumno));
    }

    @PutMapping("/profesor/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetails){
        Optional<Alumno> optionalProfesor = alumnoRepository.findById(id);
        if (!optionalProfesor.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Alumno alumno = optionalProfesor.get();
        alumno.setNombre(alumnoDetails.getNombre());
        Alumno updatedAlumno = alumnoRepository.save(alumno);
        return ResponseEntity.ok(updatedAlumno);
    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Alumno> deleteAlumno(@PathVariable Long id) {
        if (!alumnoRepository.existsById(id)){

        }
        alumnoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
