package com.mycompany.smart_campus_coursework_1989464;

import java.util.ArrayList;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.QueryParam;

// Manages the /api/v1/sensors collection
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

// GET /api/v1/sensors with optional type filter
    @GET
    public Response getAllSensors(@QueryParam("type") String type) {

        List<Sensor> sensorList = new ArrayList<>(DataStore.sensors.values());

        // If no query parameter is given, return all sensors
        if (type == null || type.isEmpty()) {
            return Response.ok(sensorList).build();
        }

        // Filter sensors by matching type
        List<Sensor> filteredList = new ArrayList<>();

        for (Sensor sensor : sensorList) {
            if (sensor.getType() != null &&
                    sensor.getType().equalsIgnoreCase(type)) {

                filteredList.add(sensor);
            }
        }

        return Response.ok(filteredList).build();
    }

    // POST /api/v1/sensors - registers a new sensor
    @POST
    public Response addSensor(Sensor sensor) {

        // Business rule: roomId in the request body must exist
        if (!DataStore.rooms.containsKey(sensor.getRoomId())) {
             throw new LinkedResourceNotFoundException("Room ID does not exist.");
        }

        String id = UUID.randomUUID().toString();
        sensor.setId(id);

        if (sensor.getStatus() == null || sensor.getStatus().isEmpty()) {
            sensor.setStatus("ACTIVE");
        }

        DataStore.sensors.put(id, sensor);

        Room room = DataStore.rooms.get(sensor.getRoomId());
        room.getSensorIds().add(id);

        return Response.status(Response.Status.CREATED)
                .entity(sensor)
                .build();
    }
    
        // Sub-resource locator for /api/v1/sensors/{sensorId}/readings
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadings(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}
