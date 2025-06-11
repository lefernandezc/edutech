package com.msvc_profesor.services;



import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.repositories.ProfesorRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    private List<Profesor> profesorList = new ArrayList<>();

    private Profesor profesorPrueba;

    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Profesor profesor = new Profesor();
            profesor.setIdProfesor((long) i+1);
            profesor.setAsignatura(faker.careProvider().medicalProfession());
            profesor.setNombre(faker.name().fullName());

            String numeroString = faker.idNumber().valid().replaceAll("-","");
            String ultimno = numeroString.substring(numeroString.length()-1);
            String restante = numeroString.substring(0,numeroString.length()-1);

            profesor.setRun(restante+"-"+ultimno);
            this.profesorList.add(profesor);

        }
        this.profesorPrueba= this.profesorList.get(0);
    }

    @Test
    @DisplayName("Devuleve todos los profesor")
    public void shouldFindAllProfesor(){
        when(profesorRepository.findAll()).thenReturn(this.profesorList);
        List<Profesor> result = profesorService.findAll();
        assertThat(result).hasSize(200);
        assertThat(result).contains(this.profesorPrueba);

        verify(profesorRepository, times())
    }

    @Test
    @DisplayName("Encontrar por id del profesor")
    public void shouldFinProfesorById(){
        when(profesorRepository.findById(1L)).thenReturn()
    }

}
