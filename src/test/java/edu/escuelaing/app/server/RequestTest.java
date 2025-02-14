package edu.escuelaing.app.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {

    @Test
    public void testRequestCreation() {
        Request request = new Request("GET", "/path");
        assertEquals("GET", request.getMethod());
        assertEquals("/path", request.getPath());
    }

    @Test
    public void testQueryParams() {
        Request request = new Request("GET", "/path");
        request.setQueryParams("name=John&age=30");
        assertEquals("John", request.getQueryParam("name"));
        assertEquals("30", request.getQueryParam("age"));
        assertNull(request.getQueryParam("nonexistent"));
    }

    @Test
    public void testEmptyQueryParams() {
        Request request = new Request("GET", "/path");
        request.setQueryParams("");
        assertNull(request.getQueryParam("name"));
    }
}