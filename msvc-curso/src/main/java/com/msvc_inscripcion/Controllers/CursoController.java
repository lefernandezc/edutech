package com.msvc_inscripcion.Controllers;

import com.msvc_inscripcion.Dtos.CursoDTO;
import com.msvc_inscripcion.Dtos.ErrorDTO;
import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Services.CursoService;
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
@RequestMapping("/api/v1/inscripcion")
@Validated
@Tag(name = "Inscripcion", description = "Esta seccion contiene los CRUD de inscripcion")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    @Operation(
            summary = "Devuelve todas los cursos",
            description = "Este metodo debe retornar un List de Curso, en caso de que no encuentre nada retorna una List vacia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornan todas los cursos")
    })
    public ResponseEntity<List<Curso>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Devuelve un curso con respecto a su id",
            description = "Este metodo debe retornar un Curso cuando es consultado mediante su id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se retornan el curso encontrada"),
            @ApiResponse(responseCode = "404", description = "Error - Curso con ID no exite", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)

            ))
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico de un curso", required = true)
    })
    public ResponseEntity<Curso> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Guarda un curso",
            description = "Con este metodo podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guardado exitoso"),
            @ApiResponse(
                    responseCode = "409",
                    description = "El curso ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    //se importa desde swagger para no interferir con el otro request body
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Curso a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Curso.class)
            )
    )
    public ResponseEntity<Curso> save(@RequestBody @Valid Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.save(curso));
    }

    @GetMapping("/{id}/profesor")
    public ResponseEntity<List<CursoDTO>> findByProfesorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.cursoService.findByProfesorId(id));
    }

}