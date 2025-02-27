package edu.escuelaing.app.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final int PORT = 35000;
    private boolean running = true;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (running) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new RequestHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        running = false;
        executorService.shutdown();
        System.out.println("Servidor detenido.");
    }
}