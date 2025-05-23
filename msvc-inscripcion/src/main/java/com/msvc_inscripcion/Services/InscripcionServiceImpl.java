package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Clients.AlumnoClientRest;
import com.msvc_inscripcion.Clients.NotasClientRest;
import com.msvc_inscripcion.Clients.ProfesorClientRest;
import com.msvc_inscripcion.Dtos.AlumnoDTO;
import com.msvc_inscripcion.Dtos.InscripcionDTO;
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
    public List<InscripcionDTO> findAll(){
        return this.inscripcionRepository.findAll().stream().map(inscripcion ->{

            Alumno alumno = null;
            try{
                alumno = this.alumnoClientRest.findById(inscripcion.getIdAlumno());
            }catch (FeignException ex){
                throw new InscripcionException("Alumnno buscado no exite");
            }

            AlumnoDTO alumnoDTO = new AlumnoDTO();
            alumnoDTO.setCorreo(alumno.getCorreo());
            alumnoDTO.setRun(alumno.getRun());
            alumnoDTO.setNombre(alumno.getNombre());

            InscripcionDTO inscripcionDTO = new InscripcionDTO();
            inscripcionDTO.setAlumno(alumnoDTO);
            return inscripcionDTO;

        }).toList();
    }

    @Override
    public Inscripcion findById(Long id){
        return this.inscripcionRepository.findById(id).orElseThrow(
                () -> new InscripcionException("La inscripcion co id: " + id + " no se encuentra en la base de datos")
        );
    }

    @Override
    public Inscripcion save(Inscripcion inscripcion){
        try{
            Alumno alumno = this.alumnoClientRest.findById(inscripcion.getIdAlumno());
        }catch (FeignException ex) {
            throw new InscripcionException("Existe problemas con la asoción inscripcion alumno");
        }
        return this.inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<Inscripcion> findByAlumnoId (Long alumnoId){
        return this.inscripcionRepository.findByIdAlumno(alumnoId);
    }
}