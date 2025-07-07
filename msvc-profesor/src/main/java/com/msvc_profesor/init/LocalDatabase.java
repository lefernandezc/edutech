package com.msvc_profesor.init;


import com.msvc_profesor.models.entilies.Profesor;
import com.msvc_profesor.repositories.ProfesorRepository;
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
public class LocalDatabase implements CommandLineRunner {

    @Autowired
    private ProfesorRepository profesorRepository;

    private static final Logger logger = LoggerFactory.getLogger(LocalDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(profesorRepository.count()==0){
            for(int i=0;i<1000;i++){
                Profesor profesor = new Profesor();
                profesor.setNombre(faker.name().fullName());
                profesor.setCorreo("caceressimon70@gmail.com");

                String numeroString = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroString.substring(numeroString.length()-1);
                String restante = numeroString.substring(0,numeroString.length()-1);

                profesor.setRun(restante+"-"+ultimo);
                logger.info("El rut que agregas es {}", profesor.getRun());
                profesor = profesorRepository.save(profesor);
                logger.info("El profesor creado es: {}", profesor);

            }
        }

    }
}
