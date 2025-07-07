package com.msvc_inscripcion.Init;

import com.msvc_inscripcion.Models.Entities.Curso;
import com.msvc_inscripcion.Repositories.CursoRepository;
import net.datafaker.Faker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker(Locale.of("es","CL"));

        if(cursoRepository.count() == 0){
            for(int i=0;i<100;i++){
                Curso curso = new Curso();

                curso.setAsignatura(faker.educator().course());
                curso.setIdProfesor(faker.number().numberBetween(1L, 100L ));

                curso = cursoRepository.save(curso);
                log.info("El curso creado es {}", curso);
            }
        }
    }
}
