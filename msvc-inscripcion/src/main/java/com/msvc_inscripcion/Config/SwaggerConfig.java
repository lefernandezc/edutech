package com.msvc_inscripcion.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api Restfull - MSVC - Inscripcion")
                        .description("Esta es la api dedicada al msvc de inscripcion")
                        .version("1.0.0")
                );
    }
}
