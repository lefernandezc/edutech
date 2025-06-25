package com.msvc_inscripcion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcInscripcionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcInscripcionApplication.class, args);
	}

}
