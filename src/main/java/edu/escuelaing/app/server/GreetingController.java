package edu.escuelaing.app.server;

//La ruta hacia este controlador es: edu.escuelaing.app.server.GreetingController
@RestController
public class GreetingController {
    //Requisito: Todos los métodos deben ser static, devuelven un String y tienen un solo párametro tipo String

    //EJ: /greeting --> Hola World
    //EJ: /greeting?name=Samuel --> Hola Samuel
    @GetMapping("/greeting")
    public static String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hola " + name;
    }
}