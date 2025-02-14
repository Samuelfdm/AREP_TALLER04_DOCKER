package edu.escuelaing.app.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // en tiempo de ejecución
@Target(ElementType.TYPE) //Para anotar solo clases
public @interface RestController {
}