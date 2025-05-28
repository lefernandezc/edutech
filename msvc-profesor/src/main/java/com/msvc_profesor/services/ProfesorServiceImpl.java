package com.msvc_profesor.services;


import com.msvc_profesor.clients.NotasClientRest;
import com.msvc_profesor.dtos.AlumnoDTO;
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
    public List<Profesor> findAll() {
        return this.profesorRepository.findAll();
    }

    @Override
    public Profesor findById(Long id) {
        return this.profesorRepository.findById(id).orElseThrow(
                () -> new ProfesorException("El profesor con id "+id+"no se encuentra en la base de datos ")
        );
    }

    @Override
    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<NotasDTO> findByNotasId(Long idProfesor) {
        Profesor profesor = this.findById(idProfesor);
        Notas notas = this.notasClientRest.findByIdProfesor(profesor.getIdProfesor());
        List<Notas> listaNotas = this.notasClientRest.findAllByIdNotas(notas.getIdNotas());

        if(listaNotas != null && !listaNotas.isEmpty()){
            return listaNotas.stream().map(nota->{
                NotasDTO dto = new NotasDTO();
                try{
                    List<Profesor> profesors = this.profesorRepository.findByIdNotas(nota.getIdProfesor());
                    if (profesors != null && !profesors.isEmpty()){
                        System.out.println("Notas");
                    }else{
                        throw new RuntimeException("Notas not found");
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
