package com.mycompany.smart_campus_coursework_1989464;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

// Stores API data in memory
public class DataStore {
    
    // Stores room in memory
    public static Map<String, Room> rooms = new HashMap<>();
    
    // Stores sensors in memory
    public static Map<String, Sensor> sensors = new HashMap<>();
    
    // Stores reading history for each sensor
    public static Map<String, List<SensorReading>> sensorReadings = new HashMap<>();

}