package com.msvc_inscripcion.Services;


import com.msvc_inscripcion.Clients.ProfesorClientRest;
import com.msvc_inscripcion.Dtos.CursoDTO;
import com.msvc_inscripcion.Dtos.ProfesorDTO;
import com.msvc_inscripcion.Exceptions.CursoException;
import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Models.Profesor;
import com.msvc_inscripcion.Repositories.CursoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorClientRest profesorClientRest;

    @Override
    public List<Curso> findAll(){
        return this.cursoRepository.findAll().stream().map(curso -> {

            Profesor profesor = null;
            try{
                profesor = this.profesorClientRest.findById(curso.getIdProfesor());
            }catch (FeignException ex){
                throw new CursoException("El profesor buscado no existe");
            }

            ProfesorDTO profesorDTO = new ProfesorDTO();
            profesorDTO.setNombre(profesor.getNombre());
            profesorDTO.setRun(profesor.getRun());
            profesorDTO.setCorreo(profesor.getCorreo());

            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setProfesor(profesorDTO);
            return cursoDTO;

        }).toList();
    }

    @Override
    public Curso findById(Long id){
        return this.cursoRepository.findById(id).orElseThrow(
                () -> new CursoException("El curso con id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Curso save(Curso curso){
        if(this.cursoRepository.findByIdCurso(curso.getIdCurso()).isPresent()){
            throw new CursoException("La inscripcion con id: "+ curso.getIdCurso()
            +" ya existe en la base de datos");
        }
        return cursoRepository.save(curso);
    }

    @Override
    public List <CursoDTO> findByProfesorId(Long profesorId){
        return this.cursoRepository.findByIdProfesor(profesorId);
    }

}