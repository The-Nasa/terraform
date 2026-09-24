package com.example.weatherapi.service;

import com.example.weatherapi.dto.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    /**
     * Gets the weather information for a given location.
     * @param location the name of the city or location
     * @return WeatherResponse containing weather details
     */
    public WeatherResponse getWeatherForLocation(String location) {
        // Mock data logic based on location
        String temp = "24°C";
        String condition = "Despejado";
        String humidity = "60%";

        if (location != null && location.equalsIgnoreCase("Tingo Maria")) {
            temp = "28°C";
            condition = "Lluvioso";
            humidity = "85%";
        } else if (location != null && location.equalsIgnoreCase("Huanuco")) {
            temp = "22°C";
            condition = "Mayormente soleado";
            humidity = "55%";
        }

        return new WeatherResponse(location, temp, condition, humidity);
    }
}
