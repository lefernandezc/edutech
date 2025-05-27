package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Clients.AlumnoClientRest;
import com.msvc_inscripcion.Clients.NotasClientRest;
import com.msvc_inscripcion.Clients.ProfesorClientRest;
import com.msvc_inscripcion.Dtos.AlumnoDTO;
import com.msvc_inscripcion.Exceptions.InscripcionException;
import com.msvc_inscripcion.Models.Alumno;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
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
    public List<Inscripcion> findAll(){
        return this.inscripcionRepository.findAll();
    }

    @Override
    public Inscripcion findById(Long id){
        return this.inscripcionRepository.findById(id).orElseThrow(
                () -> new InscripcionException("La inscripcion co id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion){
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<AlumnoDTO> findByAlumnoId(Long idInscripcion){
        Inscripcion inscripcion = this.findById(idInscripcion);
        Alumno alumno = this.alumnoClientRest.findByIdInscripcion(inscripcion.getIdInscripcion());
        List<Alumno> alumnos = this.alumnoClientRest.findAllByIdAlumno(alumno.getIdAlumnno());

        if(alumnos != null && !alumnos.isEmpty()){
            return alumnos.stream().map(alumn->{
                AlumnoDTO dto = new AlumnoDTO();
                try{
                    List<Inscripcion> inscripciones = this.inscripcionRepository.findByIdAlumno(alumn.getIdInscripcion());
                    if(inscripciones != null && !inscripciones.isEmpty()){
                        System.out.println("Alumno");
                    }else{
                        throw new RuntimeException("alumno no encontrado");
                    }
                }catch (FeignException ex){
                    throw new RuntimeException("Feign client error", ex);
                }
                return dto;
            }).toList();
        }
        return List.of();
    }
}