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
import javax.ws.rs.DELETE;

// Manages the /api/v1/rooms path
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorRoomResource {

    // GET /api/v1/rooms - returns all rooms
    @GET
    public Response getAllRooms() {
        return Response.ok(new ArrayList<>(DataStore.rooms.values())).build();
    }

    // POST /api/v1/rooms - creates a new room
    @POST
    public Response addRoom(Room room) {

        String id = UUID.randomUUID().toString();
        room.setId(id);

        DataStore.rooms.put(id, room);

        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    // GET /api/v1/rooms/{roomId} - returns one room
    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId) {

        Room room = DataStore.rooms.get(roomId);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        return Response.ok(room).build();
    }
    
     // DELETE /api/v1/rooms/{roomId} - decommision a room
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {

        Room room = DataStore.rooms.get(roomId);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Room not found")
                    .build();
        }

        // Do not delete room if sensors are still assigned
        if (!room.getSensorIds().isEmpty()) {
             throw new RoomNotEmptyException("Room cannot be deleted because it still has active sensors.");
        }

        DataStore.rooms.remove(roomId);

        return Response.ok("Room deleted successfully").build();
    }
    
}