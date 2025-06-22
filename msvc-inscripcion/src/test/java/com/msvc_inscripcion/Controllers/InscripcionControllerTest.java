package com.msvc_inscripcion.Controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.msvc_inscripcion.Models.Entities.Inscripcion;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InscripcionControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllInscripcionWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/inscripcion", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int inscripcionCount = documentContext.read("$.length()");
        assertThat(inscripcionCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idInscripcion");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray prices = documentContext.read("$..costoInscripcion");
        assertThat(prices).containsExactlyInAnyOrder(10000, 15000);
    }

    @Test
    public void shouldReturnAnInscripcionWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/inscripcion/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idInscripcion = documentContext.read("$.idInscripcion");
        assertThat(idInscripcion).isEqualTo(1);

        Integer costoInscripcion = documentContext.read("$.costoInscripcion");
        assertThat(costoInscripcion).isEqualTo(10000);
    }

    @Test
    public void shouldReturnAnInscripcionWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/inscripcion/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewInscripcion(){
        Inscripcion inscripcion = new Inscripcion(10000);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/inscripcion",inscripcion, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idInscripcion = documentContext.read("$.idInscripcion");
        assertThat(idInscripcion).isEqualTo(3);
    }

}
