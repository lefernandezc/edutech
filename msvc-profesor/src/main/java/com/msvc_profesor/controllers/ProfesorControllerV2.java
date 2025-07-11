package com.msvc_profesor.controllers;

import com.msvc_profesor.assemblers.ProfesorModelAssembler;
import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.services.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/profesor")
@Validated
@Tag(
        name = "Medico API HATEOAS",
        description = "Aquí se generar todos los métodos CRUD para profesor"
)
public class ProfesorControllerV2 {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler profesorModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todo de profesor", description = "Devielve un lista de profesor")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.ALPS_JSON_VALUE,
                            schema = @Schema(implementation = Profesor.class)
                    )


            )
    })

    public ResponseEntity<CollectionModel<EntityModel<Profesor>>> findAll(){
        List<EntityModel<Profesor>> entityModels = this.profesorService.findAll()
                .stream()
                .map(profesorModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Profesor>> collectionModel =CollectionModel.of(
                entityModels,
                linkTo(methodOn(ProfesorControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un profesor", description = "A traves del id suministrado devuelve el profesor con esa id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Profesor.class)
                    )


            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Profesor no se  encuentra, con la id suministrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name = "id", description = "este es el id unico de profesor", required = true)
    })
    public ResponseEntity<EntityModel<Profesor>> findById(@PathVariable Long id){
        EntityModel<Profesor> entityModel = this.profesorModelAssembler.toModel(
                this.profesorService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }
    @PostMapping
    @Operation(
            summary = "Guarda un profesor",
            description = "con este metodo podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses(value ={
            @ApiResponse(
                    responseCode = "201",
                    description = "guardado existo",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Profesor.class)
                    )

            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "profesor a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class)
            )
    )
    public ResponseEntity<EntityModel<Profesor>> save(@Valid @RequestBody Profesor profesor){
        Profesor profeNew = this.profesorService.save(profesor);
        EntityModel<Profesor> entityModel =this.profesorModelAssembler.toModel(profeNew);

        return ResponseEntity
                .created(linkTo(methodOn(ProfesorControllerV2.class).findById(profeNew.getIdProfesor())).toUri())
                .body(entityModel);
    }

}
