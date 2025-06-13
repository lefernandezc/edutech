package com.msvc_alumno.service;

import com.msvc_alumno.clients.IncripcionClientRest;
import com.msvc_alumno.dtos.AlumnoDTO;
import com.msvc_alumno.model.Inscripcion;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.repositories.AlumnoRepository;
import com.msvc_alumno.services.AlumnoServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private IncripcionClientRest incripcionClientRest;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private Inscripcion inscripcionTest;

    private Alumno alumnoTest;

    @BeforeEach
    public void setUp(){
        inscripcionTest = new Inscripcion(
                1L,200000,1L
        );
        alumnoTest = new Alumno(
                1L,
                "20192147-9",
                "loca",
                "localol200@edutech.cl",
                1L
        );

    }

    @Test
    @DisplayName("Deve crear un alumno")
    public void shouldCreateAlumno(){
        when(incripcionClientRest.findById(1L)).thenReturn(this.inscripcionTest);
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(this.alumnoTest);

        Alumno result = alumnoService.save(this.alumnoTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.alumnoTest);

        verify(incripcionClientRest,times(1)).findById(1L);
        verify(alumnoRepository,times(1)).save(any(Alumno.class));

    }



}
