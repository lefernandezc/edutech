package com.msvc_inscripcion.Services;


import com.msvc_inscripcion.Exceptions.InscripcionException;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
import com.msvc_inscripcion.Repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<Inscripcion> findAll(){
        return this.inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion findById(Long id){
        return this.inscripcionRepository.findById(id).orElseThrow(
                () -> new InscripcionException("La inscripcion co id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion){
        if(this.inscripcionRepository.findByInscripcion(inscripcion.getIdInscripcion()).isPresent()){
            throw new InscripcionException("La inscripcion con id: "+inscripcion.getIdInscripcion()
            +" ya existe en la base de datos");
        }
        return inscripcionRepository.save(inscripcion);
    }

}