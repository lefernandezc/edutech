package com.msvc_notas.Assemblers;

import com.msvc_notas.Controllers.NotasControllerV2;
import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Models.Entities.Notas;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NotasModelAssembler implements RepresentationModelAssembler<Notas, EntityModel<Notas>> {

    @Override
    public EntityModel<Notas> toModel(Notas entity){
        return EntityModel.of(
                entity,
                linkTo(methodOn(NotasControllerV2.class).findById(entity.getIdNotas())).withSelfRel(),
                linkTo(methodOn(NotasControllerV2.class).findAll()).withRel("Notas"),
                Link.of("http://localhost:8001/api/v1/alumno/"+ entity.getIdNotas()).withRel("alumno"),
                Link.of("http://localhost:8002/api/v1/curso/"+ entity.getIdNotas()).withRel("curso")
        );
    }
}
