package edu.escuelaing.app.server;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testServiceCreation() throws NoSuchMethodException {
        Object controller = new Object();
        Method method = controller.getClass().getMethod("toString");
        Service service = new Service(controller, method);
        assertEquals(controller, service.getControllerInstance());
        assertEquals(method, service.getMethod());
    }
}