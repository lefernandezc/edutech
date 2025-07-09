package com.msvc_profesor.services;



import com.msvc_profesor.exceptions.ProfesorException;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        verify(profesorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Encontrar por id del profesor")
    public void shouldFindProfesorById() {
        Long idInexistente = 1L;
        when(profesorRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            profesorService.findById(idInexistente);
        }).isInstanceOf(ProfesorException.class)
                .hasMessageContaining("El medico con id "+idInexistente+"no se encuentra en la base de datos");
        verify(profesorRepository,times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("deberia guardar un medico")
    public void shouldSaveProfesor(){
        when(profesorRepository.save(any(Profesor.class))).thenReturn(this.profesorPrueba);
        Profesor result = profesorService.save(this.profesorPrueba);
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(profesorPrueba);
        verify(profesorRepository,times(1)).save(any(Profesor.class));

    }

}
