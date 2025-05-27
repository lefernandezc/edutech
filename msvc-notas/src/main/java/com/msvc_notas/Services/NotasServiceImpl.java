package com.msvc_notas.Services;

import com.msvc_notas.Clients.AlumnoClientRest;
import com.msvc_notas.Dto.AlumnoDTO;
import com.msvc_notas.Exceptions.NotasException;
import com.msvc_notas.Models.Alumno;
import com.msvc_notas.Models.Entities.Notas;
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

    @Override
    public List<Notas> findALL() {
        return this.notasRepository.findAll();
    }

    @Override
    public Notas findById(Long id) {
        return this.notasRepository.findById(id).orElseThrow(
                () ->new NotasException("Notas no encontradas")
        );
    }

    @Override
    public Notas save(Notas notas) {
        return notasRepository.save(notas);
    }

    @Override
    public List <AlumnoDTO> findByAlumnoId(Long idNotas){
        Notas notas = this.findById(idNotas);
        Alumno alumno = this.alumnoClientRest.findByIdNotas(notas.getIdNotas());
        List<Alumno> alumnos =this.alumnoClientRest.findAllByIdAlumno(alumno.getIdAlumno());

        if(alumnos != null && !alumnos.isEmpty()){
            return alumnos.stream().map(alumn->{
                AlumnoDTO dto = new AlumnoDTO();
                try{
                    List<Notas> notas1 = this.notasRepository.findByIdAlumno(alumn.getIdNotas());
                    if(notas1 != null && !notas1.isEmpty()){
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
