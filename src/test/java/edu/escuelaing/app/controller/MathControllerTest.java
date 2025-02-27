package edu.escuelaing.app.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MathControllerTest {

    @Test
    void testE() {
        String response = MathController.e("");
        assertEquals(Double.toString(Math.E), response, "Debe devolver el valor de E en String");
    }

    @Test
    void testPi() {
        String response = MathController.pi("");
        assertEquals(Double.toString(Math.PI), response, "Debe devolver el valor de PI en String");
    }
}
