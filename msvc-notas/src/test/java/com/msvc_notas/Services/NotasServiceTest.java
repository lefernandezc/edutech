package com.msvc_notas.Services;

import com.msvc_notas.Dto.NotasDTO;
import com.msvc_notas.Models.Entities.Notas;
import com.msvc_notas.Repositories.NotasRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotasServiceTest {

    @Mock
    private NotasRepository notasRepository;

    @InjectMocks
    private NotasServiceImpl notasService;

    private Notas notaPrueba;

    private List<Notas> notas = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.notaPrueba = new Notas(1L, 1, 1L, 1L);
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i=0;i<100;i++){
            Notas notaCreate = new Notas();

            notaCreate.setIdNotas(faker.number().randomNumber());
            notaCreate.setNota(faker.number().numberBetween(1, 10));
            notaCreate.setIdAlumno(faker.number().randomNumber());
            notaCreate.setIdCurso(faker.number().randomNumber());

            notas.add(notaCreate);
        }
    }

    @Test
    @DisplayName("Debo listar todas las notas")
    public void shouldFindAllNotas(){

        List<Notas> nota = new ArrayList<>(this.notas);
        nota.add(notaPrueba);
        when(notasRepository.findAll()).thenReturn(nota);

        List<NotasDTO> result = notasService.findAll();

        assertThat(result).hasSize(this.notas.size() + 1)
                        .extracting("idNotas")
                                .contains(notaPrueba.getIdNotas());


        verify(notasRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar una nota")
    public void shouldFindById(){
        when(notasRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(notaPrueba));

        Notas result = notasService.findById(Long.valueOf(1L));
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(notaPrueba);
        verify(notasRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe guardar una nueva nota")
    public void shouldSaveNotas(){
        when(notasRepository.save(any(Notas.class))).thenReturn(notaPrueba);
        Notas result = notasService.save(notaPrueba);
        org.assertj.core.api.Assertions.assertThat(result).isNotNull();
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(notaPrueba);
        verify(notasRepository, times(1)).save(any(Notas.class));
    }
}
