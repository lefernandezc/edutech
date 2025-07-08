package com.msvc_notas.Services;

import com.msvc_notas.Clients.AlumnoClientRest;
import com.msvc_notas.Clients.CursoClientRest;
import com.msvc_notas.Dto.AlumnoDTO;
import com.msvc_notas.Dto.CursoDTO;
import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Dto.ProfesorDTO;
import com.msvc_notas.Exceptions.NotasException;
import com.msvc_notas.Models.Alumno;
import com.msvc_notas.Models.Curso;
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
    private CursoClientRest cursoClientRest;

    @Override
    public List<NotasDTO> findAll() {
        return this.notasRepository.findAll().stream().map(notas -> {
            Alumno alumno = null;
            try{
                alumno = this.alumnoClientRest.findById(notas.getIdAlumno());
            }catch (FeignException ex){
                throw new NotasException("El alumno buscado no existe");
            }

            Curso curso = null;
            try{
                curso = this.cursoClientRest.findById(notas.getIdCurso());
            }catch (FeignException ex){
                throw new NotasException("El profesor buscado no existe");
            }

            AlumnoDTO alumnoDTO = new AlumnoDTO();
            alumnoDTO.setNombre(alumno.getNombre());
            alumnoDTO.setRun(alumno.getRun());
            alumnoDTO.setCorreo(alumno.getCorreo());

            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setAsignatura(curso.getAsignatura());

            NotasDTO notasDTO = new NotasDTO();
            notasDTO.setAlumno(alumnoDTO);
            notasDTO.setCurso(cursoDTO);
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
            Curso curso = this.cursoClientRest.findById(notas.getIdCurso());
        }catch (FeignException ex){
            throw new NotasException("Error al consultar curso con ID: " + notas.getIdCurso());
        }

        return notasRepository.save(notas);
    }

    @Override
    public List<NotasDTO> findByAlumnoId(Long alumnoId){
       return this.notasRepository.findByIdAlumno(alumnoId);
    }

    @Override
    public List<NotasDTO> findByCursoId(Long cursoId){
        return this.notasRepository.findByIdCurso(cursoId);
    }
}
