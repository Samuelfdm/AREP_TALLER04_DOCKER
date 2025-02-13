package edu.escuelaing.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        out.println("Reflexión");

        Class c1 = String.class; //Objeto de tipo Class que representa la clase String
        Class c2 = "Ejemplo".getClass(); //Otra forma de hacerlo

        //Imprime los que están escritos en la clase y los privados
        printMembers(c1.getDeclaredConstructors(), "Declared Constructors");
        //Imprime los que están escritos en la clase, los heredados de otras clases Y NO LOS PRIVADOS
        printMembers(c2.getConstructors(), "All Constructors");
    }

    public static void printMembers(Member[] mbrs, String s) {
        out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                out.format(" %s%n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                out.format(" %s%n", ((Constructor) mbr).toGenericString());
            else if (mbr instanceof Method)
                out.format(" %s%n", ((Method) mbr).toGenericString());
        }
        if (mbrs.length == 0) {
            out.format(" -- No %s --%n", s);
        }
        out.format("%n");
    }
}