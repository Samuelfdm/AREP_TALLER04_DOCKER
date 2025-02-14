package edu.escuelaing.app.server;

import edu.escuelaing.app.MicroServer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class Router {

    public static String handleRequest(String path, Request req) {
        Map<String, Service> services = MicroServer.getServices();

        // Verifica si el path coincide con un servicio registrado
        if (services.containsKey(path)) {
            Service service = services.get(path);
            try {
                // Invoca el método del servicio y devuelve la respuesta
                return (String) service.getMethod().invoke(service.getControllerInstance(), req);
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.err.println("Error al invocar el servicio: " + e.getMessage());
            }
        }

        return "404 Not Found";
    }
}