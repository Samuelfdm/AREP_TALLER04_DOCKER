package edu.escuelaing.app.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final int PORT = getPort();
    private boolean running = true;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
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

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 35000; // Puerto por defecto en caso de no estar definido en la variable de entorno
    }
}