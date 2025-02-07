package edu.escuelaing.app.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MicroServer {

    public static Map<String, Method> services = new HashMap<String, Method>();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        loadComponents(args);
        System.out.println(simulateRequests("/greeting"));
        System.out.println(simulateRequests("/pi"));
        System.out.println(simulateRequests("/e"));
    }

    private static void loadComponents(String[] args) throws ClassNotFoundException {
        //La clase es el argumento 0
        Class c = Class.forName(args[0]);

        if(!c.isAnnotationPresent(RestController.class)){
            System.exit(0);
        }

        for(Method m : c.getDeclaredMethods()){
            if (m.isAnnotationPresent(GetMapping.class)) {
                GetMapping a = m.getAnnotation(GetMapping.class);
                services.put(a.value(), m);
            }
        }
    }

    private static String simulateRequests(String route) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Method m = services.get(route);
        String response = "\"HTTP/1.1 200 OK\\r\\n\"\n"
                + "Content-Type: application/json\\r\\n\"\n"
                + "\r\n" + "{\"resp\":\"" + m.invoke(null, "Pedro") + "\"}";
        return response;
    }
}
