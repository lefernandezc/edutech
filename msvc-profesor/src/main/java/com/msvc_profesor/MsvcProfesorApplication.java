package com.msvc_profesor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcProfesorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProfesorApplication.class, args);
	}

}