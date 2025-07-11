package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Clients.ProfesorClientRest;
import com.msvc_inscripcion.Dtos.CursoDTO;
import com.msvc_inscripcion.Exceptions.CursoException;
import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Models.Profesor;
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

    @Mock
    private ProfesorClientRest profesorClientRest;

    @InjectMocks
    private CursoServiceImpl cursoService;

    private Curso cursoPrueba;

    private List<Curso> cursoList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        this.cursoPrueba = new Curso(
                "Leanguaje"
        );
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 100; i++) {
            Curso curso = new Curso();
            curso.setIdCurso((long) i);
            curso.setAsignatura(faker.toString());

            this.cursoList.add(curso);
        }
        this.cursoPrueba = new Curso(1L, "Lenguaje", 1L);
    }

    @Test
    @DisplayName("Debo listar todas los cursos")
    public void shouldFindAllCurso() {

        List<Curso> curso = new ArrayList<>(this.cursoList);
        curso.add(cursoPrueba);

        Profesor profesor = new Profesor();
        profesor.setNombre("lolo");
        profesor.setCorreo("a@o");
        profesor.setRun("101-1");

        when(profesorClientRest.findById(anyLong())).thenReturn(profesor);
        when(cursoRepository.findAll()).thenReturn(curso);

        List<CursoDTO> result = cursoService.findAll();

        assertThat(result).hasSize(this.cursoList.size()+ 1)
                        .extracting("idCurso")
                            .contains(cursoPrueba.getIdCurso());

        verify(cursoRepository, times(1)).findAll();
        verify(profesorClientRest, atLeastOnce()).findById(anyLong());
    }

    @Test
    @DisplayName("Debe buscar un curso")
    public void shouldFindById() {
        when(cursoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(cursoPrueba));

        Curso result = cursoService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(cursoPrueba);
        verify(cursoRepository, times(1)).findById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el curso no existe")
    public void shouldNotFindCursoId() {
        Long idInexistente = 568L;
        when(cursoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            cursoService.findById(idInexistente);
        }).isInstanceOf(CursoException.class)
                .hasMessage("El curso con id: " + idInexistente + " no se encuentra en la base de datos");

        verify(cursoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo curso")
    public void shouldSaveCurso(){
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoPrueba);
        Curso result = cursoService.save(cursoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(cursoPrueba);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }
}