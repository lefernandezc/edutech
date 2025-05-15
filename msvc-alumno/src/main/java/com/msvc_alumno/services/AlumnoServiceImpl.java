package com.msvc_alumno.services;

import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.entites.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Override
    public List<AlumnoDTO> findAll() {
        return List.of();
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
