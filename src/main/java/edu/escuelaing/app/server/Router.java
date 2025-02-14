package edu.escuelaing.app.server;

import edu.escuelaing.app.MicroServer;
import edu.escuelaing.app.annotations.RequestParam;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public class Router {
    public static String handleRequest(String path, Request req) {
        Map<String, Service> services = MicroServer.getServices();

        if (!services.containsKey(path)) {
            return null;
        }

        Service service = services.get(path);
        try {
            Method method = service.getMethod();
            Object controllerInstance = service.getControllerInstance();
            String response = invokeControllerMethod(controllerInstance, method, req);

            return "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n" + response;

        } catch (Exception e) {
            e.printStackTrace();
            return "HTTP/1.1 500 Internal Server Error\r\nContent-Type: text/plain\r\n\r\nError processing request: " + e.getMessage();
        }
    }

    private static String invokeControllerMethod(Object instance, Method method, Request req) throws Exception {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            if (param.isAnnotationPresent(RequestParam.class)) {
                RequestParam requestParam = param.getAnnotation(RequestParam.class);
                String paramName = requestParam.value();
                String paramValue = req.getQueryParam(paramName);

                if (paramValue == null || paramValue.isEmpty()) {
                    paramValue = requestParam.defaultValue();
                }
                args[i] = paramValue;
            } else if (param.getType().equals(Request.class)) {
                args[i] = req; // Inyectar la solicitud completa si el método la requiere
            }
        }

        Object result = method.invoke(instance, args);
        return (result != null) ? result.toString() : "";
    }
}