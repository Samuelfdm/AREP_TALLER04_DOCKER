package edu.escuelaing.app.server;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaticFileHandlerTest {

    private ByteArrayOutputStream outputStream;
    private PrintWriter writer;
    private String testBasePath = "./src/test/resources/";

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream), true);
        StaticFileHandler.staticfiles(testBasePath);
    }

    @Test
    void testServeExistingFile() throws IOException {
        String resourcePath = "static/test.txt";
        boolean result = StaticFileHandler.serve(resourcePath, outputStream, writer);

        String response = outputStream.toString();
        System.out.println("Response:\n" + response);

        assertTrue(result, "Debe devolver true para archivos existentes.");
        assertTrue(response.contains("HTTP/1.1 200 OK"), "Debe incluir el código de estado 200.");
    }

    @Test
    void testServeNonExistingFile() {
        String resourcePath = "static/notfound.txt"; // Archivo inexistente
        boolean result = StaticFileHandler.serve(resourcePath, outputStream, writer);

        String response = outputStream.toString();
        System.out.println("Response:\n" + response);

        assertFalse(result, "Debe devolver false para archivos inexistentes.");
        assertTrue(response.contains("HTTP/1.1 404"), "Debe incluir el código de estado 404.");
    }

    @Test
    void testServeWithIOException() throws IOException {
        OutputStream mockOutputStream = mock(OutputStream.class);
        doThrow(new IOException("Simulated error")).when(mockOutputStream).write(any(byte[].class), anyInt(), anyInt());

        String resourcePath = "static/test.txt";
        boolean result = StaticFileHandler.serve(resourcePath, mockOutputStream, writer);

        String response = outputStream.toString();
        System.out.println("Response:\n" + response);

        assertFalse(result, "Debe devolver false si hay un error de IO.");
        assertTrue(response.contains("HTTP/1.1 500"), "Debe incluir el código de estado 500.");
    }
}