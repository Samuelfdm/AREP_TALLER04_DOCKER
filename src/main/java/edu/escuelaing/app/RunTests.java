package edu.escuelaing.app;

import java.lang.reflect.Method;

public class RunTests {
    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0;
        System.out.println("ARGSSSSSS " + args[0]);
        //java -cp target/classes edu.escuelaing.app.RunTests edu.escuelaing.app.Foo
        //args[0] es edu.escuelaing.app.Foo
        for (Method m : Class.forName(args[0]).getMethods()) {
            //isAnnotationPresent es un metodo de Method para identificar si existe una anotación
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);//no tienen parametros los estaticos
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                    failed++;
                }}}
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }
}