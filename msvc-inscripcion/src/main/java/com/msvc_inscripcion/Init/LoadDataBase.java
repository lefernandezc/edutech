package com.msvc_inscripcion.Init;

import com.msvc_inscripcion.Repositories.InscripcionRepository;
import net.datafaker.Faker;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Autowired
    InscripcionRepository inscripcionRepository;

    @Override
    public void run(String... args) throws Exception{
        Faker faker = new
    }
}
