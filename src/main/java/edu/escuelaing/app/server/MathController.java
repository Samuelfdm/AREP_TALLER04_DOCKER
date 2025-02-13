package edu.escuelaing.app.server;

//La ruta hacia este controlador es: edu.escuelaing.app.server.MathController
@RestController
public class MathController {

    //EJ: /e --> Math.E
    @GetMapping("/e")
    public static String e(@RequestParam(value = "name", defaultValue = "value") String valueE) {
        return Double.toString(Math.E);
    }

    //EJ: /pi --> Math.PI
    //EJ: /pi?name=12345 --> Math.PI
    @GetMapping("/pi")
    public static String pi(@RequestParam(value = "name", defaultValue = "value") String valuePI) {
        return Double.toString(Math.PI);
    }
}
