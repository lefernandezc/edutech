package com.msvc_inscripcion.Services;

import com.msvc_inscripcion.Models.Entities.Inscripcion;
import com.msvc_inscripcion.Repositories.InscripcionRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ExtendWith(MockitoExtension.class)
public class InscripcionServiceTest {

    @Mock
    private InscripcionRepository inscripcionRepository;

    @InjectMocks
    private InscripcionServiceImpl inscripcionService;

    private Inscripcion inscripcionPrueba;

    private List<Inscripcion> inscripciones = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setIdInscripcion((long) i);
            inscripcion.setCostoInscripcion(faker.number().numberBetween(10,1000000));

            this.inscripciones.add(inscripcion);
        }
        this.inscripcionPrueba = new Inscripcion(1L, 10000);
    }

}
