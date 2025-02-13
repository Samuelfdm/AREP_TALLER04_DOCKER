package edu.escuelaing.app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvokeMain {
    public static void main(String... args) { //LO QUE INGRESAMOS POR LINEA DE COMANDOS SE GUARDA AQUI
        try {//ARGS ES UN ARREGLO DE ENTRADAS ESCRITAS POR USUARIO DE TIPO STRING
            //c es un objeto que representa el tipo de CLASE en args[0]
            Class<?> c = Class.forName(args[0]); //Crea una instancia de esa clase (si la encuentra), args[0] ES EL NOMBRE DE LA CLASE
            Class[] argTypes = new Class[]{String[].class}; //La firma de un metodo es el NOMBRE + LISTA DE PARAMETROS
            //Los argTypes representa los TIPOS de los parametros de c
            //En este caso el main solo tiene un parametro String[].class

            //Ya tenemos los TIPOS de la clase y de los parametros de la clase
            //Ahora necesitamos el METODO main dentro de esa clase
            //Aqui BUSCAMOS el metodo con su nombre y TIPO de parametros
            Method main = c.getDeclaredMethod("main", argTypes);
            //DESDE args[1] EN ADELANTE ESTÁN LOS ARGUMENTOS
            //String[] Puede variar dependiendo el tipo de parametros que tenga el metodo
            String[] mainArgs = Arrays.copyOfRange(args, 1, args.length);
            System.out.format("HOLAA - invoking %s.main()%n", c.getName());
            //Aqui invocamos el metodo pasandole los VALORES de los parametros, NO los tipos
            //COLOCAMOS NULL PORQUE ES UN METODO ESTÁTICO, SI FUERA OTRO COLOCARIAMOS UN OBJETO
            main.invoke(null, (Object) mainArgs);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}