package edu.escuelaing.app.controller;

import edu.escuelaing.app.annotations.GetMapping;
import edu.escuelaing.app.annotations.RequestParam;
import edu.escuelaing.app.annotations.RestController;

//La ruta hacia este controlador es: edu.escuelaing.app.controller.MathController
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
