package com.mycompany.smart_campus_coursework_1989464;

import java.util.List;
import java.util.ArrayList;

public class Room {
    private String id; // Unique identifier for room
    private String name; // Human readable name for room
    private int capacity; // Maximum occupancy for safety
    private List<String> sensorIds = new ArrayList<>(); // Collection of IDs sensors deployed in this room
    
    
    public String getId(){
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public List<String> getSensorIds() {
        return sensorIds;
    }
    
    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }
}