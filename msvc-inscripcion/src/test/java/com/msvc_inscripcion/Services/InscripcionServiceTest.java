package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Exceptions.InscripcionException;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
import com.msvc_inscripcion.Repositories.InscripcionRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InscripcionServiceTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private InscripcionServiceImpl inscripcionService;

    private Inscripcion inscripcionPrueba;

    private List<Inscripcion> inscripciones = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        this.inscripcionPrueba = new Inscripcion(
                10000
        );
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 100; i++) {
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setIdInscripcion((long) i);
            inscripcion.setCostoInscripcion(faker.number().numberBetween(10, 1000000));

            this.inscripciones.add(inscripcion);
        }
        this.inscripcionPrueba = new Inscripcion(1L, 10000);
    }

    @Test
    @DisplayName("Debo listar todas las inscripciones")
    public void shouldFindAllInscripciones() {

        List<Inscripcion> inscripciones = this.inscripciones;
        inscripciones.add(inscripcionPrueba);
        when(inscripcionRepository.findAll()).thenReturn(inscripciones);

        List<Inscripcion> result = inscripcionService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(inscripcionPrueba);

        verify(inscripcionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar una inscripcion")
    public void shouldFindById() {
        when(inscripcionRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(inscripcionPrueba));

        Inscripcion result = inscripcionService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inscripcionPrueba);
        verify(inscripcionRepository, times(1)).findById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Debe buscar una inscripcion id que no exista")
    public void shouldNotFindInscripcionId() {
        Long idInexistente = (Long) 999L;
        when(inscripcionRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            inscripcionService.findById(idInexistente);
        }).isInstanceOf(InscripcionException.class)
                .hasMessageContaining("La inscripcion con id"+
                        idInexistente+"no se encuentra en la base de datos");
        verify(inscripcionRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva inscripcion")
    public void shouldSaveInscripcion(){
        when(inscripcionRepository.save(any(Inscripcion.class))).thenReturn(inscripcionPrueba);
        Inscripcion result = inscripcionService.save(inscripcionPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(inscripcionPrueba);
        verify(inscripcionRepository, times(1)).save(any(Inscripcion.class));
    }
}