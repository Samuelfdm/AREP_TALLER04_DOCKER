package edu.escuelaing.app.server;

import edu.escuelaing.app.MicroServer;
import edu.escuelaing.app.annotations.GetMapping;
import edu.escuelaing.app.annotations.RestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestHandlerTest {
    private MicroServer microServer;
    private RequestHandler requestHandler;
    private Socket mockSocket;

    @BeforeEach
    void setUp() throws IOException {
        microServer = new MicroServer();

        // Cargar la clase de prueba en MicroServer
        List<String> classes = new ArrayList<>();
        classes.add("edu.escuelaing.app.server.RequestHandlerTest$TestController");
        microServer.loadComponents(classes);

        // Mockeamos un socket para simular la conexión del cliente
        mockSocket = mock(Socket.class);
    }

    @Test
    void testHandleValidRequest() throws IOException {
        // Simular una petición HTTP válida a "/test"
        String request = "GET /test HTTP/1.1\r\nHost: localhost\r\n\r\n";
        InputStream inputStream = new ByteArrayInputStream(request.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(inputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        // Ejecutar el RequestHandler con el socket simulado
        requestHandler = new RequestHandler(mockSocket);
        requestHandler.run();

        // Convertimos la respuesta a String para verificar
        String response = outputStream.toString();

        // Validamos que la respuesta contenga HTTP 200 y el contenido esperado
        assertTrue(response.contains("HTTP/1.1 200 OK"), "La respuesta debe ser 200 OK.");
        assertTrue(response.contains("Hola Mundo"), "El cuerpo de la respuesta debe contener 'Hola Mundo'.");
    }

    @Test
    void testHandleInvalidRequest() throws IOException {
        // Simular una petición HTTP a una ruta inexistente
        String request = "GET /notfound HTTP/1.1\r\nHost: localhost\r\n\r\n";
        InputStream inputStream = new ByteArrayInputStream(request.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(inputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        // Ejecutar el RequestHandler con el socket simulado
        requestHandler = new RequestHandler(mockSocket);
        requestHandler.run();

        // Convertimos la respuesta a String para verificar
        String response = outputStream.toString();

        // Validamos que la respuesta contenga HTTP 404 Not Found
        assertTrue(response.contains("HTTP/1.1 404 Not Found"), "La respuesta debe ser 404 Not Found.");
    }

    // Controlador de prueba
    @RestController
    public static class TestController {
        @GetMapping("/test")
        public static String testMethod() {
            return "Hola Mundo";
        }
    }
}