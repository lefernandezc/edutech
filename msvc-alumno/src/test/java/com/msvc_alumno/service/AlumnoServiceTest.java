package com.msvc_alumno.service;

import com.msvc_alumno.clients.CursoClientRest;
import com.msvc_alumno.model.Curso;
import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.repositories.AlumnoRepository;
import com.msvc_alumno.services.AlumnoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @Mock
    private CursoClientRest cursoClientRest;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private Curso cursoTest;

    private Alumno alumnoTest;

    @BeforeEach
    public void setUp(){
        cursoTest = new Curso(
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
        when(cursoClientRest.findById(1L)).thenReturn(this.cursoTest);
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(this.alumnoTest);

        Alumno result = alumnoService.save(this.alumnoTest);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(this.alumnoTest);

        verify(cursoClientRest,times(1)).findById(1L);
        verify(alumnoRepository,times(1)).save(any(Alumno.class));

    }

    @Test
    @DisplayName("Debe buscar un alumno")
    public void shouldFindById(){
        when(alumnoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(alumnoTest));

        Alumno result = alumnoService.findById(Long.valueOf(1L));
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(alumnoTest);
        verify(alumnoRepository, times(1)).findById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Debe guardar los datos alumno")
    public void shouldSaveAlumno(){
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumnoTest);
        Alumno result = alumnoService.save(alumnoTest);
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(alumnoTest);
        verify(alumnoRepository, times(1)).save(any(Alumno.class));
    }


}
