package com.msvc_inscripcion.Services;


import com.msvc_inscripcion.Exceptions.CursoException;
import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Override
    public List<Curso> findAll(){
        return this.inscripcionRepository.findAll();
    }

    @Override
    public Curso findById(Long id){
        return this.inscripcionRepository.findById(id).orElseThrow(
                () -> new CursoException("La inscripcion co id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Curso save(Curso curso){
        if(this.inscripcionRepository.findByIdInscripcion(curso.getIdInscripcion()).isPresent()){
            throw new CursoException("La inscripcion con id: "+ curso.getIdInscripcion()
            +" ya existe en la base de datos");
        }
        return inscripcionRepository.save(curso);
    }

}