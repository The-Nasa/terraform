package com.example.weatherapi.service;

import com.example.weatherapi.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Authenticates a user and returns a JWT if successful.
     * @param username the username
     * @param password the password
     * @return an Optional containing the JWT if valid, or empty if invalid
     */
    public Optional<String> login(String username, String password) {
        // Mock authentication logic
        if ("admin".equals(username) && "1234".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return Optional.of(token);
        }
        return Optional.empty();
    }
}
