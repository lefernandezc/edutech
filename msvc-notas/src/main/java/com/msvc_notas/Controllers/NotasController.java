package com.msvc_notas.Controllers;


import com.msvc_notas.Dto.AlumnoDTO;
import com.msvc_notas.Dto.ErrorDTO;
import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Dto.ProfesorDTO;
import com.msvc_notas.Models.Entities.Notas;
import com.msvc_notas.Services.NotasService;
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

@RestController
@RequestMapping("/api/v1/notas")
@Validated
@Tag(name = "Notas", description = "Operacion CRUD de notas")
public class NotasController {

    @Autowired
    private NotasService notasService;

    @GetMapping
    @Operation(summary = "Obtiene todos las notas", description = "Devuele un List de notas en el Body")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Operacion existosa")
    })
    public ResponseEntity<List<Notas>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(notasService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una nota", description = "A través del id suministrado devuelve la nota con esa id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Operacion existosa"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Nota no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico de nota", required = true)
    })
    public ResponseEntity<Notas> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(notasService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Guarda una nota",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "La nota guardada ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "nota a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Notas.class)
            )
    )
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
