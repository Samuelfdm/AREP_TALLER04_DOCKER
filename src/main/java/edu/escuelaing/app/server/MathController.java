package edu.escuelaing.app.server;

@RestController
public class MathController {
    @GetMapping("/e")
    public static String e(String nousada) {
        return Double.toString(Math.E);
    }
}
