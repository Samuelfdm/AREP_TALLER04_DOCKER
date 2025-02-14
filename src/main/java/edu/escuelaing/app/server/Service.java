package edu.escuelaing.app.server;

import java.lang.reflect.Method;

public class Service {
    private final Object controllerInstance;
    private final Method method;

    public Service(Object controllerInstance, Method method) {
        this.controllerInstance = controllerInstance;
        this.method = method;
    }

    public Object getControllerInstance() {
        return controllerInstance;
    }

    public Method getMethod() {
        return method;
    }
}