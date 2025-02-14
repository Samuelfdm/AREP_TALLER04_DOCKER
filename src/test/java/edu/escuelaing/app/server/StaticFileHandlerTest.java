//package edu.escuelaing.app.server;
//
//import org.junit.jupiter.api.Test;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintWriter;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class StaticFileHandlerTest {
//
//    @Test
//    public void testServeExistingFile() {
//        StaticFileHandler.staticfiles("./src/test/resources/static");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintWriter writer = new PrintWriter(outputStream);
//        boolean result = StaticFileHandler.serve("/test.html", outputStream, writer);
//        assertTrue(result);
//        assertTrue(outputStream.toString().contains("HTTP/1.1 200 OK"));
//    }
//
//    @Test
//    public void testServeNonExistentFile() {
//        StaticFileHandler.staticfiles("./src/test/resources/static");
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        PrintWriter writer = new PrintWriter(outputStream);
//        boolean result = StaticFileHandler.serve("/nonexistent.html", outputStream, writer);
//        assertFalse(result);
//        assertTrue(outputStream.toString().contains("HTTP/1.1 404 Not Found"));
//    }
//}