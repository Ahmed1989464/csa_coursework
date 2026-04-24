package com.mycompany.smart_campus_coursework_1989464;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Smart_Campus_Coursework_1989464 {

    // Base URL for the API
    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    // Starts the server
    public static HttpServer startServer() {

        ResourceConfig config =
                new ResourceConfig().packages("com.mycompany.smart_campus_coursework_1989464");

        return GrizzlyHttpServerFactory.createHttpServer(
                URI.create(BASE_URI), config);
    }

    public static void main(String[] args) throws IOException {

        HttpServer server = startServer();

        System.out.println("Server started");
        System.out.println("http://localhost:8080/api/v1/");
        System.out.println("Press Enter to stop");

        System.in.read();
        server.shutdownNow();
    }
}