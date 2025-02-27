package edu.escuelaing.app.controller;

import edu.escuelaing.app.annotations.GetMapping;
import edu.escuelaing.app.annotations.RequestParam;
import edu.escuelaing.app.annotations.RestController;
import edu.escuelaing.app.server.StaticFileHandler;

//La ruta hacia este controlador es: edu.escuelaing.app.controller.GreetingController
@RestController
public class GreetingController {
    //Requisito: Todos los métodos deben ser static, devuelven un String y tienen un solo párametro tipo String

    @GetMapping("/greeting")
    public static String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hola " + name;
    }

    @GetMapping("/hello")
    public static String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "{\"name\": \"" + name + "\"}";
    }

    @GetMapping("/")
    public static String raiz(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "PÁGINA RAIZ EN PROGRESO...xd - busca /prueba.html";
    }
}