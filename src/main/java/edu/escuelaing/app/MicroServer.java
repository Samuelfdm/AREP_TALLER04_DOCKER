package edu.escuelaing.app;

import edu.escuelaing.app.annotations.GetMapping;
import edu.escuelaing.app.annotations.RestController;
import edu.escuelaing.app.server.HttpServer;
import edu.escuelaing.app.server.Service;
import edu.escuelaing.app.server.StaticFileHandler;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class MicroServer {

    public static final Map<String, Service> services = new HashMap<>();

    public static void main(String[] args) {
        try {
            // Configurar la ubicación de los archivos estáticos
            StaticFileHandler.staticfiles("/usrapp/bin/src/main/resources");

            // Buscar y cargar automáticamente los controladores anotados
            List<String> classes = getClasses("edu.escuelaing.app.controller");
            loadComponents(classes);

            // Iniciar el servidor
            HttpServer server = new HttpServer();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }

    public static void loadComponents(List<String> classes) {
        for (String className : classes) {
            try {
                Class<?> c = Class.forName(className);

                // Verificar si la clase tiene la anotación @RestController
                if (!c.isAnnotationPresent(RestController.class)) {
                    continue;
                }

                // Crear una instancia del controlador
                Object instance = c.getDeclaredConstructor().newInstance();

                // Registrar cada método anotado con @GetMapping
                for (Method method : c.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        String route = method.getAnnotation(GetMapping.class).value();
                        services.put(route, new Service(instance, method));
                    }
                }
            } catch (Exception e) {
                System.err.println("Error al cargar componente: " + className);
                e.printStackTrace();
            }
        }
    }

    public static List<String> getClasses(String packageName) {
        List<String> classNames = new ArrayList<>();
        try {
            String path = packageName.replace('.', '/');
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL packageURL = classLoader.getResource(path);

            if (packageURL != null) {
                File directory = new File(new URI(packageURL.toString()));
                if (directory.exists()) {
                    for (String fileName : Objects.requireNonNull(directory.list())) {
                        if (fileName.endsWith(".class")) {
                            String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                            classNames.add(className);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener clases en el paquete " + packageName);
            e.printStackTrace();
        }
        return classNames;
    }

    public static Map<String, Service> getServices() {
        return services;
    }
}