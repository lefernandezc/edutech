package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Clients.AlumnoClientRest;
import com.msvc_inscripcion.Clients.NotasClientRest;
import com.msvc_inscripcion.Clients.ProfesorClientRest;
import com.msvc_inscripcion.Dtos.InscripcionDTO;
import com.msvc_inscripcion.Exceptions.InscripcionException;
import com.msvc_inscripcion.Models.Alumno;
import com.msvc_inscripcion.Models.Notas;
import com.msvc_inscripcion.Models.Profesor;
import com.msvc_inscripcion.Repositories.InscripcionRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private AlumnoClientRest alumnoClientRest;

    @Autowired
    private NotasClientRest notasClientRest;

    @Autowired
    private ProfesorClientRest profesorClientRest;

    @Override
    public List<InscripcionDTO> findAll(){
        return this.inscripcionRepository.findAll().stream().map(inscripcion ->{

            Alumno alumno = null;
            try{
                alumno = this.alumnoClientRest.findById(inscripcion.getIdAlumno());
            }catch (FeignException ex){
                throw new InscripcionException("Alumnno buscado no exite");
            }

            Notas notas = null;
            Profesor profesor = null;
            try{
                notas = this.notasClientRest.findById(alumno.getIdNotas());

            }

        }
    }




}
