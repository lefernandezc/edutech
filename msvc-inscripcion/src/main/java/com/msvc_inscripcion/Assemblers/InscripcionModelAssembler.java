package com.msvc_inscripcion.Assemblers;

import com.msvc_inscripcion.Controllers.InscripcionControllerV2;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscripcionModelAssembler implements RepresentationModelAssembler<Inscripcion, EntityModel<Inscripcion>> {

    @Override
    public EntityModel<Inscripcion> toModel(Inscripcion entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(InscripcionControllerV2.class).findById(entity.getIdInscripcion())).withSelfRel(),
                linkTo(methodOn(InscripcionControllerV2.class).findAll()).withRel("inscripcion")
        );
    }
}