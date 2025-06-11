package com.msvc_inscripcion.Controllers;

import com.msvc_inscripcion.Dtos.ErrorDTO;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
import com.msvc_inscripcion.Services.InscripcionService;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inscripcion")
@Validated
@Tag(name = "Inscripcion", description = "Esta seccion contiene los CRUD de inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    @Operation(
            summary = "Devuelve todas las inscripciones",
            description = "Este metodo debe retornar un List de Inscripciones, en caso de que no encuentre nada retorna una List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornan todas las inscripciones ok")
    })
    public ResponseEntity<List<Inscripcion>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Devuelve una inscripcion con respecto a su id",
            description = "Este metodo debe retornar una Inscripcion cuando es consultado mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornan la inscripcion encontrada"),
            @ApiResponse(responseCode = "404", description = "Error - Inscripcion con ID no exite", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)

            ))
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de una inscripcion", required = true)
    })
    public ResponseEntity<Inscripcion> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.inscripcionService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = ""
    )
    //se importa desde swagger para no interferir con el otro request body
    @io.swagger.v3.oas.annotations.parameters.RequestBody(

    )
    public ResponseEntity<Inscripcion> save(@RequestBody @Valid Inscripcion inscripcion){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.inscripcionService.save(inscripcion));
    }

}
