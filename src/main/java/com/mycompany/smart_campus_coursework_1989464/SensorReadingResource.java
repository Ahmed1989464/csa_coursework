package com.mycompany.smart_campus_coursework_1989464;

import java.util.ArrayList;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Dedicated resource class for sensor readings
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private String sensorId;

    // Stores the parent sensor ID for this readings resource
    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    // GET /api/v1/sensors/{sensorId}/readings - fetch reading history
    @GET
    public Response getReadingHistory() {

        if (!DataStore.sensors.containsKey(sensorId)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }

        if (!DataStore.sensorReadings.containsKey(sensorId)) {
            DataStore.sensorReadings.put(sensorId, new ArrayList<SensorReading>());
        }

        return Response.ok(DataStore.sensorReadings.get(sensorId)).build();
    }

    // POST /api/v1/sensors/{sensorId}/readings - append new reading
    @POST
    public Response addReading(SensorReading reading) {

        Sensor sensor = DataStore.sensors.get(sensorId);

        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Sensor not found")
                    .build();
        }
        
        // Maintenance sensors cannot accept readings
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor is in maintenance and cannot accept new readings.");
        }

        String id = UUID.randomUUID().toString();
        reading.setId(id);

        if (reading.getTimestamp() == 0) {
            reading.setTimestamp(System.currentTimeMillis());
        }

        if (!DataStore.sensorReadings.containsKey(sensorId)) {
            DataStore.sensorReadings.put(sensorId, new ArrayList<SensorReading>());
        }

        DataStore.sensorReadings.get(sensorId).add(reading);

        // Side effect: update parent sensor currentValue
        sensor.setCurrentValue(reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
}