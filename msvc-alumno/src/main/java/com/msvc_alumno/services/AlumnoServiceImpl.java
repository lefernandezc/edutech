package com.msvc_alumno.services;

import com.msvc_alumno.clients.IncripcionClientRest;
import com.msvc_alumno.dtos.AlumnoDTO;
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
    public List<AlumnoDTO> findAll() {
        return this.alumnoRepository.findAll().stream().map(alumno -> {
            Inscripcion inscripcion =null;
            try{
                inscripcion = this.incripcionClientRest.findById(alumno.getIdInscripcion());
            }catch (FeignException ex){
                throw new AlumnoException("El alumno buscado no existe");
            }

            InscripcionDTO inscripcionDTO = new InscripcionDTO();
            inscripcionDTO.setCostoInscripcion(inscripcion.getCostoInscripcion());

            return null;
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
            Inscripcion inscripcion = this.incripcionClientRest.findById(alumno.getIdInscripcion());
        }catch (FeignException ex){
            throw new AlumnoException("existe problema con la asocion inscripcion");
        }
        return alumnoRepository.save(alumno);
    }
    @Override
    public List<Alumno> findByInscripcionId(Long inscripcionId){return this.alumnoRepository.findByIdInscripcion(inscripcionId);}


}



