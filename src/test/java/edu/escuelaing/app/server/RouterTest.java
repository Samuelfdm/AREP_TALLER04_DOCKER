package edu.escuelaing.app.server;

import edu.escuelaing.app.MicroServer;
import edu.escuelaing.app.annotations.GetMapping;
import edu.escuelaing.app.annotations.RestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class RouterTest {

    @RestController
    public static class TestController {
        @GetMapping("/test")
        public String testMethod() {
            return "Hello, World!";
        }
    }

    @BeforeEach
    public void setUp() throws Exception {
        MicroServer.getServices().clear();
        Object controller = new TestController();
        Method method = controller.getClass().getMethod("testMethod");
        MicroServer.getServices().put("/test", new Service(controller, method));
    }

    @Test
    public void testHandleRequest() {
        Request request = new Request("GET", "/test");
        String response = Router.handleRequest("/test", request);
        assertNotNull(response);
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Hello, World!"));
    }

    @Test
    public void testHandleRequestNotFound() {
        Request request = new Request("GET", "/nonexistent");
        String response = Router.handleRequest("/nonexistent", request);
        assertNull(response);
    }
}