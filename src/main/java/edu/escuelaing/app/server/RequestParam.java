package edu.escuelaing.app.server;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)//Para anotar solo parámetros
public @interface RequestParam {
    String value();
    String defaultValue();
}