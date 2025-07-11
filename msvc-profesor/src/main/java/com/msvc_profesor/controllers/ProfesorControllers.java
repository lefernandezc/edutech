package com.msvc_profesor.controllers;


import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.repositories.ProfesorRepository;
import com.msvc_profesor.services.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/profesor")
@Validated
@Tag(
        name = "Profesor API",
        description = "Aqui se generar todos los metodos CRUD para profesro"
)
public class ProfesorControllers {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    @Operation(
            summary = "Endpoint que obtiene todos lo de profesor",
            description = "Este endpoint devuelve un listadob de todo lo de profesor que se encuentre"+
                    "en la base de datos"
    )
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion de extracion de profesor exitosa"
            )
    })
    public ResponseEntity<List<Profesor>> findAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.profesorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Endpoint que devuelve un profesor por id",
            description = "Endpoint que va devolver un profesor.class al momento de buscarlo por id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion por id correcto"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Error cuando el profesor con cierto id no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)

                    )
            )
    })
    @Parameters(value = {
            @Parameter(
                    name = "id",
                    description = "Primary key - Entidad profesor",
                    required = true
            )
    })
    public ResponseEntity<Profesor> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.profesorService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Endpoint guardar de un profesor",
            description = "Endpoint que me permite capturar un elemento Profesor.class y lo guarde"+
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
                    schema = @Schema(implementation = Profesor.class)
            )
    )
    public ResponseEntity<Profesor> create(@Valid @RequestBody Profesor profesor){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.profesorService.save(profesor));
    }

    @PutMapping("/profesor/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @RequestBody Profesor profesorDetails){
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        if (!optionalProfesor.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Profesor profesor = optionalProfesor.get();
        profesor.setNombre(profesorDetails.getNombre());
        Profesor updatedProfesor = profesorRepository.save(profesor);
        return ResponseEntity.ok(updatedProfesor);
    }

    @DeleteMapping("/profesor/{id}")
    public ResponseEntity<Profesor> deleteProfedor(@PathVariable Long id) {
        if (!profesorRepository.existsById(id)){

        }
        profesorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
