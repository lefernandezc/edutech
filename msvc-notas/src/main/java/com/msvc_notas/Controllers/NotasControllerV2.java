package com.msvc_notas.Controllers;

import com.msvc_notas.Assemblers.NotasModelAssembler;
import com.msvc_notas.Dto.ErrorDTO;
import com.msvc_notas.Dto.NotasDTO;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2/notas")
@Validated
@Tag(name = "Notas V2", description = "Operaciones CRUD de notas hateoas")
public class NotasControllerV2 {

    @Autowired
    private NotasService notasService;

    @Autowired
    private NotasModelAssembler notasModelAssembler;

    @GetMapping
    @Operation(summary = "Obtiene todos las notas", description = "Devuele un List de Notas en el Body")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Notas.class)
                    )
            )
    })
    public ResponseEntity<CollectionModel<EntityModel<NotasDTO>>> findAll(){
        List<EntityModel<NotasDTO>> entityModels = this.notasService.findAll()
                .stream()
                .map(notasModelAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<NotasDTO>> collectionModel = CollectionModel.of(
                entityModels,
                linkTo(methodOn(NotasControllerV2.class).findAll()).withSelfRel()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collectionModel);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una nota", description = "A través del id suministrado devuelve la nota con esa id")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operacion existosa",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Notas.class)
                    )
            ),
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

    public ResponseEntity<EntityModel<Notas>> findById(@PathVariable Long id){
        EntityModel<Notas> entityModel = this.notasModelAssembler.toModel(
                this.notasService.findById(id)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }
    @PostMapping
    @Operation(
            summary = "Guarda un medico",
            description = "Con este método podemos enviar los datos mediante un body y realizar el guardado"
    )
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Guardado exitoso",
                    content = @Content(
                            mediaType = MediaTypes.HAL_JSON_VALUE,
                            schema = @Schema(implementation = Notas.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El medico guardado ya se encuentra en la base de datos",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    )
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "medico a crear",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Notas.class)
            )
    )
    public ResponseEntity<EntityModel<Notas>>  save(@Valid @RequestBody Notas notas) {
        Notas notaNew = this.notasService.save(notas);
        EntityModel<Notas> entityModel = this.notasModelAssembler.toModel(notaNew);

        return ResponseEntity
                .created(linkTo(methodOn(NotasControllerV2.class).findById(notaNew.getIdNotas())).toUri())
                .body(entityModel);
    }

}
