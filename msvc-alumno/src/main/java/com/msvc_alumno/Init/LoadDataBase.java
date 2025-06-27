package com.msvc_alumno.Init;

import com.msvc_alumno.model.entites.Alumno;
import com.msvc_alumno.repositories.AlumnoRepository;
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
    AlumnoRepository alumnoRepository;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new Faker(Locale.of("es", "CL"));

        if (alumnoRepository.count()== 0){
            for (int i=0;i<100;i++){
                Alumno alumno = new Alumno();

                String numeroStr = faker.idNumber().valid().replaceAll("-","");
                String ultimo = numeroStr.substring(numeroStr.length()-1);
                String restante = numeroStr.substring(0, numeroStr.length()-1);

                alumno.setNombre(faker.name().fullName());
                alumno.setRun(restante+"-"+ultimo);
                alumno.setCorreo(faker.internet().emailAddress());
                alumno.setIdCurso(faker.number().randomNumber());

                alumno = alumnoRepository.save(alumno);
                log.info("El alumno creado es {}", alumno);
            }
        }

    }
}
