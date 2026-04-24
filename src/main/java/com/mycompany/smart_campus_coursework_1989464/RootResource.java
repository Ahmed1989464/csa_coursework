package com.mycompany.smart_campus_coursework_1989464;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Root Discovery endpoint at GET /api/v1
@Path("/")
public class RootResource {

    // Returns API metadata as JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getDiscoveryInfo() {

        Map<String, Object> response = new HashMap<>();

        // Versioning info
        response.put("version", "v1");

        // Administrative contact details
        response.put("contact", "1989464@university.ac.uk");

        // Map of primary resource collections
        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        response.put("resources", resources);

        return response;
    }
}
