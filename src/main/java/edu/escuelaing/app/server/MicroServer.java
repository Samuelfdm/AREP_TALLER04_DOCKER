package edu.escuelaing.app.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MicroServer {

    //Mapa con ruta del método y el método a ejecutar
    //Representan todos los servicios que ofrecen las clases @RestController
    //En este caso solo hay una, si hubieran más clases RestController toca buscarlas por disco
    public static Map<String, Method> services = new HashMap<String, Method>();

    //ESTA IMPLEMENTACIÓN UTILIZA LA CONSOLA PARA MANDARLE LA UBICACIÓN DE LOS COMPONENTES/CONTROLADORES QUE TIENEN LAS ANOTACIONES
    //String[] args --> args[0] es el main y de ahi en adelante son todos los componentes
    //java -cp target/classes edu.escuelaing.app.server.MicroServer edu.escuelaing.app.server.GreetingController edu.escuelaing.app.server.MathController

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        loadComponents(args); //Va a cargar todos los servicios/métodos de TODOS LOS @RestController que encuentre
        System.out.println(simulateRequests("/greeting"));
        System.out.println(simulateRequests("/pi"));
        System.out.println(simulateRequests("/pi?name=JOSE")); //El query lo omite por la implementación
        System.out.println(simulateRequests("/e"));
        System.out.println(simulateRequests("/e?name=MARTA")); //El query lo omite por la implementación
        System.out.println(simulateRequests("/greeting?name=DIANAA"));
        System.out.println(simulateRequests("/greeting?name=DIEGOO"));
    }

    private static void loadComponents(String[] args) throws ClassNotFoundException {
        //La clase MAIN es el args[0]
        for (String arg : args) {
            System.out.printf("ARG: %s%n", arg);
            Class c = Class.forName(arg); //Creamos un objeto c de tipo Class que representa la clase args[i]

            //Si la clase cargada NO es un Controlador no seguimos buscando
            if(!c.isAnnotationPresent(RestController.class)){
                System.exit(0);
            }

            //Si la clase cargada es un Controlador buscamos todos sus metodos ANOTADOS con GetMapping
            for(Method m : c.getDeclaredMethods()){
                if (m.isAnnotationPresent(GetMapping.class)) {
                    GetMapping a = m.getAnnotation(GetMapping.class);
                    services.put(a.value(), m); //a.value() es la ruta definida en GetMapping hacia el método m
                }
            }
        }
    }

    private static String simulateRequests(String route) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        //Para simular una petición, buscamos en route el metodo/servicio que quiero ejecutar
        //SI LA PETICIÓN TIENE QUERY, TOCA SEPARAR LA ROUTE DEL QUERY
        String query = "MESSI"; //ESTO REEMPLAZA EL defaultValue = "World"
        if (route.contains("?")) {
            query = route.substring(route.indexOf("=")+1);
            route = route.substring(0, route.indexOf("?"));
        }

        System.out.println("RUTAAAA: "+ route);
        System.out.println("QUERYYY: "+ query);

        Method m = services.get(route);
        String response = "\"HTTP/1.1 200 OK\\r\\n\"\n"
                + "Content-Type: application/json\\r\\n\"\n"
                + "\r\n" + "{\"resp\":\"" + m.invoke(null, query) + "\"}";

        return response;
    }
}