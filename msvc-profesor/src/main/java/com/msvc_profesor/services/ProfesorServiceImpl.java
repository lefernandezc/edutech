package com.msvc_profesor.services;

import com.msvc_profesor.clients.AlumnoClientRest;
import com.msvc_profesor.clients.NotasClientRest;
import com.msvc_profesor.dtos.AlumnoDTO;
import com.msvc_profesor.dtos.NotasDTO;
import com.msvc_profesor.dtos.ProfesorDTO;
import com.msvc_profesor.exceptions.ProfesorException;
import com.msvc_profesor.models.Alumno;
import com.msvc_profesor.models.Notas;
import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.repositories.ProfesorRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AlumnoClientRest alumnoClientRest;

    @Autowired
    private NotasClientRest notasClientRest;

    @Override
    public List<ProfesorDTO> findAll() {
        return this.profesorRepository.findAll().stream().map(profesor -> {

            Alumno alumno = null;
            try{
                alumno = this.alumnoClientRest.findById(profesor.getIdAlumno());
            }catch (FeignException ex){
                throw new ProfesorException("El alumno buscado no existe");
            }

            Notas notas = null;
            try{
                notas = this.notasClientRest.findById(profesor.getIdNotas());
            }catch (FeignException ex){
                throw new ProfesorException("Las notas no existe en la base de datos");
            }

            AlumnoDTO alumnoDTO = new AlumnoDTO();
            alumnoDTO.setRun(alumno.getRun());
            alumnoDTO.setNombre(alumno.getNombre());
            alumnoDTO.setCorreo(alumno.getCorreo());

            NotasDTO notasDTO= new NotasDTO();
            notasDTO.setNotas(notas.getNota());

            ProfesorDTO profesorDTO = new ProfesorDTO();
            profesorDTO.setNombre(profesor.getNombre());
            profesorDTO.setAsignatura(profesor.getAsignatura());
            profesorDTO.setRun(profesor.getCorreo());
            profesorDTO.setCorreo(profesor.getCorreo());
            profesorDTO.setAlumno(alumnoDTO);
            profesorDTO.setNotas(notasDTO);

            return profesorDTO;
        }).toList();
    }

    @Override
    public Profesor findById(Long id) {
        return this.profesorRepository.findById(id).orElseThrow(
                () -> new ProfesorException("El profesor con id "+id+"no se encuentra en la base de datos ")
        );
    }

    @Override
    public Profesor save(Profesor profesor) {
        try{
            Alumno alumno = this.alumnoClientRest.findById(profesor.getIdAlumno());
            Notas notas = this.notasClientRest.findById(profesor.getIdNotas());
        }catch (FeignException ex){
            throw new ProfesorException("existe el problema con la asociacion del alumno del profesor");
        }
        return this.profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> findByAlumnoId(Long alumnoid) {
        return this.profesorRepository.findByIdAlumno(alumnoid);
    }

    @Override
    public List<Profesor> findByNotasId(Long notasid) {
        return this.profesorRepository.findByIdNotas(notasid);
    }
}
