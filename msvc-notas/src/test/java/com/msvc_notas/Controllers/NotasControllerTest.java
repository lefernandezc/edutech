package com.msvc_notas.Controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.msvc_notas.Models.Entities.Notas;
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
public class NotasControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllNotasWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/notas", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int notasCount = documentContext.read("$.length()");
        assertThat(notasCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idNotas");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray notas = documentContext.read("$..Nota");
        assertThat(notas).containsExactlyInAnyOrder(5, 7);
    }

    @Test
    public void shouldReturnAnNotasWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/notas/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idNotas = documentContext.read("$.idNotas");
        assertThat(idNotas).isEqualTo(1);

        Integer nota = documentContext.read("$.nota");
        assertThat(nota).isEqualTo(7);
    }

    @Test
    public void shouldReturnAnNotasWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/notas/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewNotas(){
        Notas notas = new Notas(1L, 10, 1L,1L);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/notas",notas, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idNotas = documentContext.read("$.idNotas");
        assertThat(idNotas).isEqualTo(3);
    }
}
