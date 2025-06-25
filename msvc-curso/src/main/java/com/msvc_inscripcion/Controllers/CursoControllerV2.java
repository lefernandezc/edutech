package com.msvc_inscripcion.Controllers;

import com.msvc_inscripcion.Assemblers.CursoModelAssembler;
import com.msvc_inscripcion.Dtos.ErrorDTO;
import com.msvc_inscripcion.Models.Entities.Curso;
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
@RequestMapping("/api/v2/curso")
@Validated
@Tag(name = "Curso V2", description = "Operaciones CRUD de inscripcion hateoas")
public class CursoControllerV2 {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private CursoModelAssembler cursoModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos las inscripciones", description = "Devuelve un List de Inscripciones en el Body")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Curso.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<Curso>>> findAll(){
        List<EntityModel<Curso>> entityModels = this.inscripcionService.findAll()
                .stream()
                .map(cursoModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<Curso>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(CursoControllerV2.class).findAll()).withSelfRel()
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una inscripcion", description = "A través del id suministrado devuelve la inscripcion con esa id")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Curso.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Inscripcion no encontrado, con el id suministrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema =  @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @Parameters(value = {
            @Parameter(name="id", description = "Este es el id unico de la inscripcion", required = true)
    })
    public ResponseEntity<EntityModel<Curso>> findById(@PathVariable Long id){
        EntityModel<Curso> entityModel = this.cursoModelAssembler.toModel(
                this.inscripcionService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @PostMapping
    @Operation(
            summary = "Guarda una inscripcion",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Curso.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "La inscripcion guardada ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "inscripcion a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Curso.class)
            )
    )
    public ResponseEntity<EntityModel<Curso>> save(@Valid @RequestBody Curso curso){
        Curso inscripNew = this.inscripcionService.save(curso);
        EntityModel<Curso> entityModel = this.cursoModelAssembler.toModel(inscripNew);

        return ResponseEntity
                .created(linkTo(methodOn(CursoControllerV2.class).findById(inscripNew.getIdInscripcion())).toUri())
                .body(entityModel);
    }

}
