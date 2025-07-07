package com.msvc_inscripcion.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        Contact contact = new Contact();
        contact.setName("Leandro Fernandez");
        contact.setEmail("le.fernandezc@duocuc.cl");
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - Curso")
                        .description("Esta es la api dedicada al msvc de curso")
                        .version("1.0.0")
                        .contact(contact)
                        .summary("esto es una api dentro de un proyecto MSVC")

                );
    }
}
