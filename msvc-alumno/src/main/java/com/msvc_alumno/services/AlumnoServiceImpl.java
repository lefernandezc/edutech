package com.msvc_alumno.services;

import com.msvc_alumno.clients.CursoClientRest;
import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.dtos.CursoDTO;
import com.msvc_alumno.exceptions.AlumnoException;
import com.msvc_alumno.model.Curso;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.repositories.AlumnoRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;


    @Autowired
    private CursoClientRest cursoClientRest;
    @Override
    public List<Alumno> findAll() {
        return this.alumnoRepository.findAll().stream().map(alumno -> {
            Curso curso =null;
            try{
                curso = this.cursoClientRest.findById(alumno.getIdCurso());
            }catch (FeignException ex){
                throw new AlumnoException("El alumno buscado no existe");
            }

            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setCostoCurso(curso.getCostoCurso());

            AlumnoDTO alumnoDTO = new AlumnoDTO();
            alumnoDTO.setCurso(cursoDTO);

            return alumno;
        }).toList();
    }

    @Override
    public Alumno findById(Long id) {
        return this.alumnoRepository.findById(id).orElseThrow(
                () -> new AlumnoException("El alumno con id " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Alumno save(Alumno alumno) {
        try{
            Curso curso = this.cursoClientRest.findById(alumno.getIdCurso());
        }catch (FeignException ex){
            throw new AlumnoException("existe problema con la asocion Curso");
        }
        return alumnoRepository.save(alumno);
    }
    @Override
    public List<Alumno> findByCursoId(Long cursoId){return this.alumnoRepository.findByIdCurso(cursoId);}


}



