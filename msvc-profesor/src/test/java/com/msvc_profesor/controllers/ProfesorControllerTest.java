package com.msvc_profesor.controllers;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.msvc_profesor.models.entilies.Profesor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfesorControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    @DisplayName("Debe entregar un listado con todos los medicos")
    public void shouldReturnAllProfesorWhenListIsRequired(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/profesor",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int profesorCount = documentContext.read("$.length()");
        assertThat(profesorCount).isEqualTo(1000);
    }


    @Test
    public void shouldReturnAnProfesorWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/profesor/1", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProfesor = documentContext.read("$.idProfesor");
        Assertions.assertThat(idProfesor).isEqualTo(1);

    }

    @Test
    public void shouldReturnAnprofesorWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/profesor/9999", String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        Assertions.assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewProfesor(){
        Profesor profesor = new Profesor();
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/profesor",profesor, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idProfesor = documentContext.read("$.idProfesor");
        Assertions.assertThat(idProfesor).isEqualTo(3);
    }

}
