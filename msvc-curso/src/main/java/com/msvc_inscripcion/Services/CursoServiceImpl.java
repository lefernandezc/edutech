package com.msvc_inscripcion.Services;


import com.msvc_inscripcion.Exceptions.CursoException;
import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> findAll(){
        return this.cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id){
        return this.cursoRepository.findById(id).orElseThrow(
                () -> new CursoException("La inscripcion co id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Curso save(Curso curso){
        if(this.cursoRepository.findByIdInscripcion(curso.getIdInscripcion()).isPresent()){
            throw new CursoException("La inscripcion con id: "+ curso.getIdInscripcion()
            +" ya existe en la base de datos");
        }
        return cursoRepository.save(curso);
    }

}