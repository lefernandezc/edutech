package com.msvc_alumno.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.msvc_alumno.model.entites.Alumno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlumnoControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Debe entregar un listado con todos los alumno")
    public void shouldRetunAllAlumnoWhenListIsRequired(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/alumno", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int alumnoCount = documentContext.read("$.length()");
        assertThat(alumnoCount).isEqualTo(1000);
    }


    @Test
    public void shouldReturnAnAlumnoWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/alumno/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idALumno = documentContext.read("$.idAlumno");
        assertThat(idALumno).isEqualTo(1);


    }

    @Test
    public void shouldReturnAnAlumnoWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/alumno/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewAlumno(){
        Alumno alumno = new Alumno();
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/alumno",alumno, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idAlumno = documentContext.read("$.idAlumno");
        assertThat(idAlumno).isEqualTo(3);
    }


}
