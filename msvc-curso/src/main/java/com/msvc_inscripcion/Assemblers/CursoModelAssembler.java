package com.msvc_inscripcion.Assemblers;

import com.msvc_inscripcion.Controllers.CursoControllerV2;
import com.msvc_inscripcion.Models.Entities.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(CursoControllerV2.class).findById(entity.getIdInscripcion())).withSelfRel(),
                linkTo(methodOn(CursoControllerV2.class).findAll()).withRel("inscripcion")
        );
    }
}