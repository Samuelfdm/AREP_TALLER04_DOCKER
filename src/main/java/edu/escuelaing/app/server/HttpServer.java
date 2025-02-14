package edu.escuelaing.app.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final int PORT = 35000;
    private boolean running = true;

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (running) {
                Socket clientSocket = serverSocket.accept();
                new RequestHandler(clientSocket).handle();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    public void stop() {
        running = false;
        System.out.println("Servidor detenido.");
    }
}