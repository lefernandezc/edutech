package com.msvc_alumno.controllers;

import com.msvc_alumno.assemblers.AlumnoModelAssembler;
import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.services.AlumnoService;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/alumno")
@Validated
@Tag(name = "alumno V2", description = "Operaciones CRUD de inscripcion hateoas")
public class AlumnoControllerV2 {

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private AlumnoModelAssembler alumnoModelAssembler;

    @GetMapping
    @Operation(summary = "Obttiene todo de alumno", description = "Devuelve un listado de alumno")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Alumno.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Alumno>>> findAll(){
        List<EntityModel<Alumno>> entityModels = this.alumnoService.findAll()
                .stream()
                .map(alumnoModelAssembler::toModel)
                .toList();
        CollectionModel<EntityModel<Alumno>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(AlumnoControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }
    @GetMapping("/id")
    @Operation(summary = "Obtiene un alumno", description = "A traves del id suministrado devuelve la inscripcion con esa id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Alumno.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Alumno no se encuentra, con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "Este es el id unico del alumno", required = true)
    })
    public ResponseEntity<EntityModel<Alumno>> findById(@PathVariable Long id){
        EntityModel<Alumno> entityModel = this.alumnoModelAssembler.toModel(
                this.alumnoService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }
    @PostMapping
    @Operation(
            summary = "Guarda un alumno",
            description = "con este metodo podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Alumno.class)
                    )
            ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "El alumno guardo y se encuentra en la base de datos",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Error.class)
                            )
                    )
            })
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "inscricion a crear",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Alumno.class)
                    )
            )
            public ResponseEntity<EntityModel<Alumno>> save(@Valid @RequestBody Alumno alumno){
                Alumno alumNew = this.alumnoService.save(alumno);
                EntityModel<Alumno> entityModel= this.alumnoModelAssembler.toModel(alumNew);
                return ResponseEntity
                        .created(linkTo(methodOn(AlumnoControllerV2.class).findById(alumNew.getIdAlumno())).toUri())
                        .body(entityModel);
    }

}
