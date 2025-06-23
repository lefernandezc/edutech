package com.msvc_notas.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        Contact contact = new Contact();
        contact.setName("Leandro Fernandez");
        contact.setEmail("le.fernandezc@duocuc.cl");
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - Notas")
                        .description("Esta es la api dedicada al msvc de Notas")
                        .version("1.0.0")
                        .contact(contact)
                        .summary("esto es una api dentro de un proyecto MSVC")

                );
    }
}
