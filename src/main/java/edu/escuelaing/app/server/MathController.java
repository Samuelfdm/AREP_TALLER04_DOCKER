package edu.escuelaing.app.server;

//La ruta hacia este controlador es: edu.escuelaing.app.server.MathController
@RestController
public class MathController {

    //EJ: /e --> Math.E
    @GetMapping("/e")
    public static String e(String nousada) {
        return Double.toString(Math.E);
    }
}
