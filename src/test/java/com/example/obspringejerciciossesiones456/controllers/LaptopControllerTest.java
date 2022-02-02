package com.example.obspringejerciciossesiones456.controllers;

import com.example.obspringejerciciossesiones456.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

        //Crear un objeto que este presente en todas las pruebas
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "marca": "Lenovo",
                    "precio": 1142.99,
                    "procesador": "Core i3",
                    "ram": 8,
                    "videoDedicado": false,
                    "touchScreen": false
                }
                """;

        HttpEntity<String> requestCreate = new HttpEntity<>(json, headers);
        testRestTemplate.exchange("/api/laptops", HttpMethod.POST, requestCreate, Laptop.class);
    }

    @Test
    void findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Laptop[]> response = testRestTemplate.exchange("/api/laptops", HttpMethod.GET, request, Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(Arrays.toString(response.getBody()));
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json = """
                {
                    "marca": "Toshiba",
                    "precio": 1499.99,
                    "procesador": "Core i7",
                    "ram": 16,
                    "videoDedicado": true,
                    "touchScreen": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);
        Laptop result = response.getBody();
        //assertEquals(2L, result.getId());
        assertEquals("Toshiba", result.getMarca());
        assertEquals(1499.99, result.getPrecio());

        findAll();
    }

    @Test
    void update() {
        findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        //Actualizar el objeto
        String jsonModificado = """
                {
                    "id": 1,
                    "marca": "Lenovo modificado para el test",
                    "precio": 1312.99,
                    "procesador": "Core i5",
                    "ram": 4,
                    "videoDedicado": false,
                    "touchScreen": true
                }
                """;
        HttpEntity<String> requestUpdate = new HttpEntity<>(jsonModificado,headers);
        ResponseEntity<Laptop> responseUpdate = testRestTemplate.exchange("/api/laptops",HttpMethod.PUT,requestUpdate,Laptop.class);
        Laptop resultUpdate = responseUpdate.getBody();
        //System.out.println(response);
        assertEquals("Lenovo modificado para el test", resultUpdate.getMarca());
        assertEquals("Core i5", resultUpdate.getProcesador());

        findAll();
    }

    @Test
    void delete() {
        //Agregar otro objeto
        create();

        //Borrar el objeto

        //Opcion 1
        //testRestTemplate.delete("/api/laptops/"+ String.valueOf(id));
        //Verificar que el objeto no existe
        //ResponseEntity<Laptop> response1 = testRestTemplate.getForEntity("/api/laptops/"+ String.valueOf(id), Laptop.class);
        //assertEquals(HttpStatus.NOT_FOUND, response1.getStatusCode());

        //Opcion 2
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops/1",
                HttpMethod.DELETE, request, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response2.getStatusCode());

        findAll();
    }

    @Test
    void deleteAll() {
        //Agregar otro objeto
        create();

        //Eliminar todos
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, request, Laptop.class);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        findAll();
    }
}