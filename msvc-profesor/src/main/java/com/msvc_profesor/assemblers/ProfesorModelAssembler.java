package com.msvc_profesor.assemblers;

import com.msvc_profesor.controllers.ProfesorControllerV2;
import com.msvc_profesor.models.entilies.Profesor;
import org.hibernate.boot.jaxb.hbm.internal.RepresentationModeConverter;
import org.hibernate.metamodel.mapping.EntityValuedModelPart;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {


    @Override
    public EntityModel<Profesor> toModel(Profesor entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(ProfesorControllerV2.class).findById(entity.getIdProfesor())).withSelfRel(),
                linkTo(methodOn(ProfesorControllerV2.class).findAll()).withRel("profesor")
        );
    }
}
