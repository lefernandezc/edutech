package com.msvc_profesor.services;


import com.msvc_profesor.clients.NotasClientRest;
import com.msvc_profesor.dtos.NotasDTO;
import com.msvc_profesor.dtos.ProfesorDTO;
import com.msvc_profesor.exceptions.ProfesorException;
import com.msvc_profesor.models.Notas;
import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.repositories.ProfesorRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    private ProfesorRepository profesorRepository;


    @Autowired
    private NotasClientRest notasClientRest;

    @Override
    public List<ProfesorDTO> findAll() {
        return this.profesorRepository.findAll().stream().map(profesor -> {


            Notas notas = null;
            try{
                notas = this.notasClientRest.findById(profesor.getIdNotas());
            }catch (FeignException ex){
                throw new ProfesorException("Las notas no existe en la base de datos");
            }

            NotasDTO notasDTO= new NotasDTO();
            notasDTO.setNotas(notas.getNota());

            ProfesorDTO profesorDTO = new ProfesorDTO();
            profesorDTO.setNombre(profesor.getNombre());
            profesorDTO.setAsignatura(profesor.getAsignatura());
            profesorDTO.setRun(profesor.getCorreo());
            profesorDTO.setCorreo(profesor.getCorreo());
            profesorDTO.setNotas(notasDTO);

            return profesorDTO;
        }).toList();
    }

    @Override
    public Profesor findById(Long id) {
        return this.profesorRepository.findById(id).orElseThrow(
                () -> new ProfesorException("El profesor con id "+id+"no se encuentra en la base de datos ")
        );
    }

    @Override
    public Profesor save(Profesor profesor) {
        try{
            Notas notas = this.notasClientRest.findById(profesor.getIdNotas());
        }catch (FeignException ex){
            throw new ProfesorException("existe el problema con la asociacion del alumno del profesor");
        }
        return this.profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> findByNotasId(Long notasid) {
        return this.profesorRepository.findByIdNotas(notasid);
    }
}
