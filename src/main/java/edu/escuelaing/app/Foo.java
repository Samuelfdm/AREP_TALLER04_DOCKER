package edu.escuelaing.app;
//EJEMPLO DE ANOTACIONES RANDOM
//SOLO SIRVEN CUANDO YO MISMO CREO UNA FORMA DE IMPLEMENTARLAS
//EL COMPILADOR DE JAVA ES EL QUE HACE LA MAGIA
public class Foo {
    @Test
    public static void m1() {
    }

    public static void m2() {
    }

    @Test
    public static void m3() {
        throw new RuntimeException("Boom");
    }

    public static void m4() {
    }

    @Test
    public static void m5() {
    }

    public static void m6() {
    }

    @Test
    public static void m7() {
        throw new RuntimeException("Crash");
    }

    public static void m8() {
    }
}