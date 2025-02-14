package edu.escuelaing.app.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpServerTest {

    @Test
    public void testStartAndStop() {
        HttpServer server = new HttpServer();
        Thread serverThread = new Thread(() -> {
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        assertTrue(serverThread.isAlive());
        server.stop();
        try {
            serverThread.join(1000); // Esperar a que el hilo termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(serverThread.isAlive());
    }
}