package com.msvc_notas.Init;

import com.msvc_notas.Models.Entities.Notas;
import com.msvc_notas.Repositories.NotasRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    NotasRepository notasRepository;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker(Locale.of("es","CL"));

        if(notasRepository.count() == 0){
            for(int i=0;i<100;i++){
                Notas nota = new Notas();

                nota.setNota(faker.number().numberBetween(1 , 7 ));
                nota.setIdAlumno(faker.number().randomNumber());
                nota.setIdProfesor(faker.number().randomNumber());

                nota = notasRepository.save(nota);
                log.info("La inscripcion creada es {}", nota);
            }
        }
    }
}
