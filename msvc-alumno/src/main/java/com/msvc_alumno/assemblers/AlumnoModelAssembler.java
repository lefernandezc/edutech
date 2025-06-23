package com.msvc_alumno.assemblers;


import com.msvc_alumno.controllers.AlumnoControllerV2;
import com.msvc_alumno.model.entites.Alumno;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlumnoModelAssembler implements RepresentationModelAssembler<Alumno,EntityModel<Alumno>>{
    @Override
    public EntityModel<Alumno> toModel(Alumno entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(AlumnoControllerV2.class).findById(entity.getIdAlumno())).withSelfRel(),
                linkTo(methodOn(AlumnoControllerV2.class).findAll()).withRel("Alumno"),

        );


    }
}
