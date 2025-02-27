package edu.escuelaing.app;

import edu.escuelaing.app.annotations.GetMapping;
import edu.escuelaing.app.annotations.RestController;
import edu.escuelaing.app.server.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MicroServerTest {
    private MicroServer microServer;

    @BeforeEach
    void setUp() {
        microServer = new MicroServer();
    }

    @Test
    void testRegisterController() throws NoSuchMethodException {
        // Cargamos un componente especifico de prueba
        List<String> classes = new ArrayList<>();
        classes.add("edu.escuelaing.app.MicroServerTest$TestController"); // Clase completa con el paquete
        microServer.loadComponents(classes);

        // Obtener el mapa de rutas registradas
        Map<String, Service> routes = microServer.getServices();

        // Verificamos que la ruta /test esté registrada
        assertTrue(routes.containsKey("/test"), "La ruta /test debería estar registrada.");

        // Verificamos que el método registrado es el esperado
        Method expectedMethod = TestController.class.getMethod("testMethod");
        assertEquals(expectedMethod, routes.get("/test").getMethod(), "El método registrado no coincide con el esperado.");
    }

    // Controlador de prueba
    @RestController
    public static class TestController {
        @GetMapping("/test")
        public static String testMethod() {
            return "Hola Mundo";
        }
    }

    @Test
    public void testGetClasses() {
        List<String> classes = MicroServer.getClasses("edu.escuelaing.app");
        assertFalse(classes.isEmpty());
    }
}
