package com.msvc_notas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcNotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcNotasApplication.class, args);
	}

}
