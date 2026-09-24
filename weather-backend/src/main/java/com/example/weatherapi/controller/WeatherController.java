package com.example.weatherapi.controller;

import com.example.weatherapi.dto.WeatherResponse;
import com.example.weatherapi.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String location) {
        WeatherResponse response = weatherService.getWeatherForLocation(location);
        return ResponseEntity.ok(response);
    }
}
