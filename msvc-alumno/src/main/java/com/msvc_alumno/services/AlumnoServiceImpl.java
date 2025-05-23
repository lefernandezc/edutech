package com.msvc_alumno.services;

import com.msvc_alumno.clients.IncripcionClientRest;
import com.msvc_alumno.clients.NotasClientRest;
import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.dtos.InscripcionDTO;
import com.msvc_alumno.dtos.NotasDTO;
import com.msvc_alumno.exceptions.AlumnoException;
import com.msvc_alumno.model.Inscripcion;
import com.msvc_alumno.model.Notas;
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
    private NotasClientRest notasClientRest;

    @Autowired
    private IncripcionClientRest incripcionClientRest;

    @Override
    public List<AlumnoDTO> findAll() {

        return this.alumnoRepository.findAll().stream().map(alumno -> {

            Inscripcion inscripcion = null;
            try {
                inscripcion = this.incripcionClientRest.findById(alumno.getIdInscripcion());
            } catch (FeignException.FeignClientException ex) {
                throw new AlumnoException("El alumno buscado no existe");
            }


            Notas notas = null;
            try {
                notas = this.notasClientRest.findById(alumno.getIdNotas());
            } catch (FeignException ex) {
                throw new AlumnoException("Las notas no se encuentra en la base de datos");
            }

            InscripcionDTO inscripcionDTO = new InscripcionDTO();
            inscripcionDTO.setCosto(inscripcion.getCosto());
            inscripcionDTO.setNombreAsignatura(inscripcion.getNombreAsignatura());

            NotasDTO notasDTO = new NotasDTO();
            notasDTO.setNotas(notas.getNota());


        }).toList();
    }

    @Override
    public Alumno findById(Long id) {
        return this.alumnoRepository.findById(id).orElseThrow(
                () -> new AlumnoException("el alumno con id: "+id+ "no se encuentra en la base de datos")
        );
    }

    @Override
    public Alumno save(Alumno alumno) {
        try{
            Inscripcion inscripcion = this.incripcionClientRest.findById(alumno.getIdInscripcion());
            Notas notas = this.notasClientRest.findById(alumno.getIdNotas());
        }catch (FeignException ex){
            throw new AlumnoException("el Existe el problema de la asociacion");
        }
        return this.alumnoRepository.save(alumno);
    }

    @Override
    public List<Alumno> findByInscripcionId(Long inscripcionId) {
        return this.alumnoRepository.findByIdInscripcion(inscripcionId);;
    }

    @Override
    public List<Alumno> findByNotasId(Long notasId) {

        return this.alumnoRepository.findByIdNotas(notasId);
    }


}
