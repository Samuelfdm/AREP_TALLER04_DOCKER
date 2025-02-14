package edu.escuelaing.app.server;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import static org.junit.jupiter.api.Assertions.*;

public class RequestHandlerTest {

    @Test
    public void testHandleRequest() {
        String request = "GET /test HTTP/1.1\r\nHost: localhost\r\n\r\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Socket socket = new Socket() {
            @Override
            public java.io.InputStream getInputStream() {
                return inputStream;
            }

            @Override
            public java.io.OutputStream getOutputStream() {
                return outputStream;
            }
        };
        RequestHandler handler = new RequestHandler(socket);
        handler.handle();
        String response = outputStream.toString();
        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
    }
}