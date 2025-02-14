package edu.escuelaing.app.server;

import edu.escuelaing.app.server.Response;
import edu.escuelaing.app.server.ResponseHelper;
import edu.escuelaing.app.server.Router;
import edu.escuelaing.app.server.StaticFileHandler;

import java.io.*;
import java.net.Socket;

public class RequestHandler {
    private final Socket clientSocket;

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handle() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream();
             PrintWriter writer = new PrintWriter(out, true)) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            System.out.println("Received: " + requestLine);
            String[] requestParts = requestLine.split(" ");

            if (requestParts.length < 2) {
                ResponseHelper.sendErrorResponse(writer, 400, "Bad Request");
                return;
            }

            String method = requestParts[0];
            String path = requestParts[1];

            // Crear objetos Request y Response
            Request req = new Request(method, path);
            Response res = new Response();

            // Verificar si hay parámetros en la URL
            if (path.contains("?")) {
                String[] pathAndQuery = path.split("\\?", 2);
                path = pathAndQuery[0];
                req.setQueryParams(pathAndQuery[1]);
            }
            System.out.println(path);
            // Intentar manejar la solicitud con el Router (IoC)
            String response = Router.handleRequest(path, req);
            if (response != null) {
                ResponseHelper.sendJsonResponse(writer, response);
            } else {
                // Servir archivos estáticos si la ruta no es manejada por el Router
                boolean fileServed = StaticFileHandler.serve(path, out, writer);
                if (!fileServed) {
                    ResponseHelper.sendErrorResponse(writer, 404, "Not Found");
                }
            }
        } catch (IOException e) {
            System.err.println("Error procesando la solicitud: " + e.getMessage());
        }
    }
}