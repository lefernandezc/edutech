package com.msvc_notas.Services;

import com.msvc_notas.Clients.AlumnoClientRest;
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
        try{
            Alumno alumno = this.alumnoClientRest.findById(notas.getIdAlumno());
        }catch (FeignException ex){
            throw new NotasException("El alumno con id :"+notas.getIdAlumno()+"no esta en la base de datos");
        }
        return this.notasRepository.save(notas);
    }
}
