package com.msvc_notas.Services;

import com.msvc_notas.Clients.AlumnoClientRest;
import com.msvc_notas.Clients.ProfesorClientRest;
import com.msvc_notas.Dto.AlumnoDTO;
import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Dto.ProfesorDTO;
import com.msvc_notas.Exceptions.NotasException;
import com.msvc_notas.Models.Alumno;
import com.msvc_notas.Models.Entities.Notas;
import com.msvc_notas.Models.Profesor;
import com.msvc_notas.Repositories.NotasRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotasServiceImpl implements NotasService {

    @Autowired
    private NotasRepository notasRepository;

    @Autowired
    private AlumnoClientRest alumnoClientRest;

    @Autowired
    private ProfesorClientRest profesorClientRest;

    @Override
    public List<NotasDTO> findAll() {
        return this.notasRepository.findAll().stream().map(notas -> {
            Alumno alumno = null;
            try{
                alumno = this.alumnoClientRest.findById(notas.getIdAlumno());
            }catch (FeignException ex){
                throw new NotasException("El alumno buscado no existe");
            }

            Profesor profesor = null;
            try{
                profesor = this.profesorClientRest.findById(notas.getIdProfesor());
            }catch (FeignException ex){
                throw new NotasException("El profesor buscado no existe");
            }

            AlumnoDTO alumnoDTO = new AlumnoDTO();
            alumnoDTO.setNombre(alumno.getNombre());
            alumnoDTO.setRun(alumno.getRun());
            alumnoDTO.setCorreo(alumno.getCorreo());

            ProfesorDTO profesorDTO = new ProfesorDTO();
            profesorDTO.setNombre(profesor.getNombre());
            profesorDTO.setRun(profesor.getRun());
            profesorDTO.setCorreo(profesor.getCorreo());
            profesorDTO.setAsignatura(profesor.getAsignatura());

            NotasDTO notasDTO = new NotasDTO();
            notasDTO.setAlumno(alumnoDTO);
            notasDTO.setProfesor(profesorDTO);
            return notasDTO;

        }).toList();
    }

    @Override
    public Notas findById(Long id) {
        return this.notasRepository.findById(id).orElseThrow(
                () ->new NotasException("Notas no encontradas")
        );
    }

    @Override
    public Notas save(Notas notas) {
        try{
            Alumno alumno = this.alumnoClientRest.findById(notas.getIdAlumno());

        }catch (FeignException ex){
            throw new NotasException("Error al consultar alumno con ID: " + notas.getIdAlumno());
        }

        try{
            Profesor profesor = this.profesorClientRest.findById(notas.getIdProfesor());
        }catch (FeignException ex){
            throw new NotasException("Error al consultar profesor con ID: " + notas.getIdProfesor());
        }

        return notasRepository.save(notas);
    }

    @Override
    public List<Notas> findByAlumnoId(Long alumnoId){
       return this.notasRepository.findByIdAlumno(alumnoId);
    }

    @Override
    public List<Notas> findByProfesorId(Long profesorId){
        return this.notasRepository.findByIdProfesor(profesorId);
    }
}
