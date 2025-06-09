package com.msvc_alumno.service;

import com.msvc_alumno.dtos.AlumnoDTO;
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

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private List<Alumno> alumnoList = new ArrayList<>();

    private Alumno alumnoPrueba;

    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Alumno alumno = new Alumno();
            alumno.setIdAlumno((long) i+1);
            alumno.setNombre(faker.name().fullName());
            alumno.setCorreo(faker.internet().emailAddress());

            String numeroString = faker.idNumber().valid().replaceAll("-","");
            String ultimo = numeroString.substring(numeroString.length()-1);
            String restante = numeroString.substring(0,numeroString.length()-1);

            alumno.setRun(restante+"-"+ultimo);
            this.alumnoList.add(alumno);
        }
        this.alumnoPrueba = this.alumnoList.get(0);
    }

    @Test
    @DisplayName("Devuele todos los alumnos")
    public void shouldFindAllAlumnos(){
        when(alumnoRepository.findAll()).thenReturn(this.alumnoList);
        List<AlumnoDTO> result = alumnoService.findAll();
        assertThat(result).hasSize(200);
        assertThat(result).contains(this.alumnoPrueba);

    }
}
