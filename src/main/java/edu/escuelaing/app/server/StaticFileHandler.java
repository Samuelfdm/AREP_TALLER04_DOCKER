package edu.escuelaing.app.server;

import java.io.*;

public class StaticFileHandler {
    private static String basePath = "./src/main/resources";

    public static void staticfiles(String path) {
        basePath = path;
    }

    public static boolean serve(String resourcePath, OutputStream out, PrintWriter writer) {
        System.out.println("ENTRO Serving " + basePath + resourcePath);
        File file = new File(basePath + resourcePath);
        if (file.exists() && file.isFile()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                String contentType = ResponseHelper.getContentType(file.getName());
                out.write(("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n").getBytes());
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                return true; // Indicar que se manejó correctamente
            } catch (IOException e) {
                ResponseHelper.sendErrorResponse(writer, 500, "Internal Server Error");
            }
        } else {
            ResponseHelper.sendErrorResponse(writer, 404, "Not Found");
        }
        return false;
    }
}