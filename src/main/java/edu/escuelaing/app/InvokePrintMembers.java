package edu.escuelaing.app;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;

public class InvokePrintMembers {
    public static void main(String... args) {
        try {
            //Obtenga un objeto que representa la clase
            Class<?> c = Class.forName(args[0]);
            //Obtenemos la lista de tipos de los parametros del metodo
            //Recordemos la firma: printMembers(Member[] mbrs, String s)
            Class[] argTypes = new Class[]{Member[].class, String.class};

            //Ahora necesitamos el METODO printMembers dentro de la clase c
            //Aqui BUSCAMOS el metodo con su nombre y el TIPO de sus parametros
            Method m = c.getDeclaredMethod("printMembers", argTypes);

            //Esto lo necesito porque uno de los parametros del metodo es de tipo LIST
            //Podria poner un tipo de lista cualquiera de los compatibles.
            Class otraClase = LinkedList.class;

            System.out.format("HOLAA 2 - invoking %s.printMembers()%n", c.getName());
            m.invoke(null, otraClase.getDeclaredFields(), "Fields");
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
