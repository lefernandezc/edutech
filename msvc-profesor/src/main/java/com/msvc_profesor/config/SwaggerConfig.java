package com.msvc_profesor.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact = new Contact();
        contact.setName("simon caceres");
        contact.setEmail("caceressimon70@gmail.com");
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - profesor")
                        .description("Esta es la api dedicada al msvc de profesor")
                        .version("1.0.0")
                        .contact(contact)
                        .summary("esto es una api dentro de un proyecto MSVC")
                );


    }
}
