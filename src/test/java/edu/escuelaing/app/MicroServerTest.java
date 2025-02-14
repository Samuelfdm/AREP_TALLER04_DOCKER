//package edu.escuelaing.app;
//
//import edu.escuelaing.app.annotations.GetMapping;
//import edu.escuelaing.app.annotations.RestController;
//import edu.escuelaing.app.server.Service;
//import org.junit.jupiter.api.Test;
//import java.util.List;
//import java.util.Map;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class MicroServerTest {
//
//    @RestController
//    public static class TestController {
//        @GetMapping("/test")
//        public String testMethod() {
//            return "Hello, World!";
//        }
//    }
//
//    @Test
//    public void testLoadComponents() {
//        List<String> classes = List.of("edu.escuelaing.app.edu.escuelaing.app.MicroServerTest$TestController");
//        MicroServer.loadComponents(classes);
//        Map<String, Service> services = MicroServer.getServices();
//        assertTrue(services.containsKey("/test"));
//    }
//
//    @Test
//    public void testGetClasses() {
//        List<String> classes = MicroServer.getClasses("edu.escuelaing.app");
//        assertFalse(classes.isEmpty());
//    }
//}