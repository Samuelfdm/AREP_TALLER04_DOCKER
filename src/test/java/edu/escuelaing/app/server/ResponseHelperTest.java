package edu.escuelaing.app.server;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseHelperTest {

    @Test
    public void testSendJsonResponse() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        ResponseHelper.sendJsonResponse(writer, "{\"message\": \"Hello\"}");
        writer.flush();
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Content-Type: application/json"));
        assertTrue(response.contains("{\"message\": \"Hello\"}"));
    }

    @Test
    public void testSendErrorResponse() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        ResponseHelper.sendErrorResponse(writer, 404, "Not Found");
        writer.flush();
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
        assertTrue(response.contains("Content-Type: text/html"));
        assertTrue(response.contains("<h1>404 Not Found</h1>"));
    }
}