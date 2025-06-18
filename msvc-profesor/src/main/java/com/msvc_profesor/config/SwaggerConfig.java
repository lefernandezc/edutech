package com.msvc_profesor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api 2026 Reservas de salas")
                        .version("1.0")
                        .description("Documentacion de la api para la el sistema de reserva de salas")
                );


    }

}
