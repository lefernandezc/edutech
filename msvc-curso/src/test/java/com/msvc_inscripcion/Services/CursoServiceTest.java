package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Exceptions.CursoException;
import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Repositories.CursoRepository;
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
public class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoServiceImpl inscripcionService;

    private Curso cursoPrueba;

    private List<Curso> inscripciones = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        this.cursoPrueba = new Curso(
                "10000"
        );
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 100; i++) {
            Curso curso = new Curso();
            curso.setIdCurso((long) i);
            curso.setAsignatura(faker.toString());

            this.inscripciones.add(curso);
        }
        this.cursoPrueba = new Curso(1L, "10000", 1L);
    }

    @Test
    @DisplayName("Debo listar todas las inscripciones")
    public void shouldFindAllInscripciones() {

        List<Curso> inscripciones = this.inscripciones;
        inscripciones.add(cursoPrueba);
        when(cursoRepository.findAll()).thenReturn(inscripciones);

        List<Curso> result = inscripcionService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(cursoPrueba);

        verify(cursoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe buscar una inscripcion")
    public void shouldFindById() {
        when(cursoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(cursoPrueba));

        Curso result = inscripcionService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(cursoPrueba);
        verify(cursoRepository, times(1)).findById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Debe buscar una inscripcion id que no exista")
    public void shouldNotFindInscripcionId() {
        Long idInexistente = (Long) 999L;
        when(cursoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            inscripcionService.findById(idInexistente);
        }).isInstanceOf(CursoException.class)
                .hasMessageContaining("La inscripcion con id"+
                        idInexistente+"no se encuentra en la base de datos");
        verify(cursoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva inscripcion")
    public void shouldSaveInscripcion(){
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoPrueba);
        Curso result = inscripcionService.save(cursoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(cursoPrueba);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }
}