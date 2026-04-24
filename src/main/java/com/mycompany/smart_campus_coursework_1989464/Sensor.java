package com.mycompany.smart_campus_coursework_1989464;


public class Sensor {
    
    private String id; // Unique Identifier for sensor
    private String type; // Category eg. Temperature
    private String status; // Current status of sensor
    private double currentValue; // The most recent measurment recorded
    private String roomId; // Foreign key linking to the Room where the sensor is located
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getCurrentValue() {
        return currentValue;
    }
    
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
    
    public String getRoomId() {
        return roomId;
    }
    
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
