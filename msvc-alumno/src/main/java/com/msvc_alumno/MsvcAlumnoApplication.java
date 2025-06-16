package com.msvc_alumno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class 	MsvcAlumnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcAlumnoApplication.class, args);
	}

}
