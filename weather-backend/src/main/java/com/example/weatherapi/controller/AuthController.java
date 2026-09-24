package com.example.weatherapi.controller;

import com.example.weatherapi.dto.LoginRequest;
import com.example.weatherapi.dto.LoginResponse;
import com.example.weatherapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<String> tokenOpt = authService.login(request.getUsername(), request.getPassword());
        
        if (tokenOpt.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(tokenOpt.get()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
