package com.example.weatherapi.controller;

import com.example.weatherapi.dto.WeatherResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String location) {
        // Mock data logic based on location
        String temp = "24°C";
        String condition = "Despejado";
        String humidity = "60%";

        if (location.equalsIgnoreCase("Tingo Maria")) {
            temp = "28°C";
            condition = "Lluvioso";
            humidity = "85%";
        } else if (location.equalsIgnoreCase("Huanuco")) {
            temp = "22°C";
            condition = "Mayormente soleado";
            humidity = "55%";
        }

        WeatherResponse response = new WeatherResponse(location, temp, condition, humidity);
        return ResponseEntity.ok(response);
    }
}
