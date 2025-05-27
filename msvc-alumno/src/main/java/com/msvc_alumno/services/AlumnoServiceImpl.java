package com.msvc_alumno.services;

import com.msvc_alumno.clients.IncripcionClientRest;
import com.msvc_alumno.clients.NotasClientRest;
import com.msvc_alumno.clients.ProfesorClientRest;
import com.msvc_alumno.dtos.InscripcionDTO;
import com.msvc_alumno.dtos.NotasDTO;
import com.msvc_alumno.dtos.ProfesorDTO;
import com.msvc_alumno.exceptions.AlumnoException;
import com.msvc_alumno.model.Inscripcion;
import com.msvc_alumno.model.Notas;
import com.msvc_alumno.model.Profesor;
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

    @Autowired
    private ProfesorClientRest profesorClientRest;

    @Override
    public List<Alumno> findAll() {
        return this.alumnoRepository.findAll();
    }

    @Override
    public Alumno findById(Long id){
        return this.alumnoRepository.findById(id).orElseThrow(
                () -> new AlumnoException("El alumno con id "+id+" no se encuentra en la base de datos")
        );
    }
    @Override
    public Alumno save(Alumno alumno){
        return alumnoRepository.save(alumno);
    }

    @Override
    public List<InscripcionDTO> findByInscripcionId(Long idAlumno) {
        Alumno alumno = this.findById(idAlumno);
        List<Inscripcion> inscripciones = this.incripcionClientRest.findByIdAlumno(alumno.getIdAlumno());

        if(!inscripciones.isEmpty()) {
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

    @Override
    public List<NotasDTO> findByNotasId(Long idAlumno){
        Alumno alumno = this.findById(idAlumno);
        Notas notas = this.notasClientRest.findByIdAlumno(alumno.getIdAlumno());
        List<Notas> listaNotas = this.notasClientRest.findAllByIdNotas(notas.getIdNotas());

        if (listaNotas != null && !listaNotas.isEmpty()) {
            return listaNotas.stream().map(nota -> {
                NotasDTO dto = new NotasDTO();
                try {
                    List<Alumno> alumnos = this.alumnoRepository.findByIdNotas(nota.getIdAlumno());
                    if (alumnos != null && !alumnos.isEmpty()) {
                        System.out.println("Notas");
                    } else {
                        throw new RuntimeException("Notas not found");
                    }
                } catch (FeignException ex) {
                    throw new RuntimeException("Feign client error", ex);
                }

                return dto;
            }).toList();
        }
        return List.of();
    }

    @Override
    public List<ProfesorDTO> findByProfesorId(Long idAlumno){
        Alumno alumno = this.findById(idAlumno);
        Profesor profesor = this.profesorClientRest.findByIdAlumno(alumno.getIdAlumno());
        List<Profesor> profesores = this.profesorClientRest.findAllByIdProfesor(profesor.getIdProfesor());

        if (profesores != null && !profesores.isEmpty()) {
            return profesores.stream().map(profe -> {
                ProfesorDTO dto = new ProfesorDTO();
                try {
                    List<Alumno> alumnos = this.alumnoRepository.findByIdProfesor(profe.getIdAlumno());
                    if (alumnos != null && !alumnos.isEmpty()) {
                        System.out.println("profesor");
                    } else {
                        throw new RuntimeException("profesor not found");
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





