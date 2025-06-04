package com.msvc_alumno.services;

import com.msvc_alumno.clients.IncripcionClientRest;
import com.msvc_alumno.dtos.InscripcionDTO;
import com.msvc_alumno.exceptions.AlumnoException;
import com.msvc_alumno.model.Inscripcion;
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
    private IncripcionClientRest incripcionClientRest;



    @Override
    public List<Alumno> findAll() {
        return this.alumnoRepository.findAll();
    }

    @Override
    public Alumno findById(Long id) {
        return this.alumnoRepository.findById(id).orElseThrow(
                () -> new AlumnoException("El alumno con id " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<InscripcionDTO> findByInscripcionId(Long idAlumno) {
        Alumno alumno = this.findById(idAlumno);
        List<Inscripcion> inscripciones = this.incripcionClientRest.findByIdAlumno(alumno.getIdAlumno());

        if (!inscripciones.isEmpty()) {
            return inscripciones.stream().map(inscripcion -> {
                InscripcionDTO dto = new InscripcionDTO();
                try {
                    List<Alumno> alumnos = this.alumnoRepository.findByIdInscripcion(inscripcion.getIdAlumno());
                    if (alumnos != null && !alumnos.isEmpty()) {
                        System.out.println("alumno");
                    } else {
                        throw new RuntimeException("Alumno not found");
                    }
                } catch (FeignException ex) {
                    throw new RuntimeException("Feign client error", ex);
                }

                return dto;
            }).toList();
        }
        return List.of();
    }

}



