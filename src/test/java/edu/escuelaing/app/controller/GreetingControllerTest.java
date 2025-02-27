package edu.escuelaing.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class GreetingControllerTest {

    @Test
    void testGreetingWithName() {
        String response = GreetingController.greeting("Samuel");
        assertEquals("Hola Samuel", response, "El saludo debe incluir el nombre proporcionado");
    }

    @Test
    void testGreetingWithDefault() {
        String response = GreetingController.greeting("");
        assertEquals("Hola ", response, "Debe devolver 'Hola ' si el nombre es vacío");
    }

    @Test
    void testHelloWithName() {
        String response = GreetingController.hello("Felipe");
        assertEquals("{\"name\": \"Felipe\"}", response, "Debe devolver un JSON con el nombre");
    }

    @Test
    void testHelloWithDefault() {
        String response = GreetingController.hello("");
        assertEquals("{\"name\": \"\"}", response, "Debe devolver un JSON con un nombre vacío");
    }

    @Test
    void testRaiz() {
        String response = GreetingController.raiz("");
        assertEquals("PÁGINA RAIZ EN PROGRESO...xd - busca /prueba.html", response, "Debe devolver el mensaje de página raíz");
    }
}
