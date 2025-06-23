package com.msvc_notas.Assemblers;

import com.msvc_notas.Controllers.NotasControllerV2;
import com.msvc_notas.Dto.NotasDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NotasModelAssembler implements RepresentationModelAssembler<NotasDTO, EntityModel<NotasDTO>> {

    @Override
    public EntityModel<NotasDTO> toModel(NotasDTO dto){
        return EntityModel.of(
                dto,
                linkTo(methodOn(NotasControllerV2.class).findById(dto.get())).withSelfRel(),
                linkTo(methodOn(NotasControllerV2.class).findAll()).withRel("Notas"),
                Link.of("http://localhost:8001/api/v1/alumno/"+ dto.getIdNotas()).withRel("alumno"),
                Link.of("http://localhost:8004/api/v1/profesor/"+ dto.getIdNotas()).withRel("profesor")
        );
    }
}
