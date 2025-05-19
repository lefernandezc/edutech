package com.msvc_alumno.services;

import com.msvc_alumno.clients.IncripcionClientRest;
import com.msvc_alumno.clients.NotasClientRest;
import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.Inscripcion;
import com.msvc_alumno.model.Notas;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.repositories.AlumnoRepository;
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

    @Override
    public List<AlumnoDTO> findAll() {

        return this.alumnoRepository.findAll().stream().map(alumno -> {

            Inscripcion inscripcion = null;
            try{
                inscripcion = this.incripcionClientRest.findById(alumno.getIdInscripcion())
            }


        }).toList();
    }

    @Override
    public Alumno findById(Long id) {
        return null;
    }

    @Override
    public Alumno save(Alumno alumno) {
        return null;
    }
}
