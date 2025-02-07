package edu.escuelaing.app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvokeMain {
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]); //Crea una instancia de esa clase, args[0] ES EL NOMBRE DE LA CLASE
            Class[] argTypes = new Class[]{String[].class}; //La firma de un metodo es el NOMBRE + LISTA DE PARAMETROS
            //Los argTypes representa los tipos de los parametros
            //En este caso el main solo tiene un parametro String[].class
            Method main = c.getDeclaredMethod("main", argTypes);
            String[] mainArgs = Arrays.copyOfRange(args, 1, args.length); //DESDE args[1] EN ADELANTE SON LOS ARGUMENTOS
            System.out.format("invoking %s.main()%n", c.getName());
            main.invoke(null, (Object) mainArgs);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}