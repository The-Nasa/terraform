package com.example.weatherapi.dto;

public class WeatherResponse {
    private String location;
    private String temperature;
    private String condition;
    private String humidity;

    public WeatherResponse(String location, String temperature, String condition, String humidity) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
    }

    // Getters and Setters
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getTemperature() { return temperature; }
    public void setTemperature(String temperature) { this.temperature = temperature; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public String getHumidity() { return humidity; }
    public void setHumidity(String humidity) { this.humidity = humidity; }
}
