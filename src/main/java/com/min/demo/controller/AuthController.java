package com.min.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.min.demo.entities.Utilisateur;
import com.min.demo.repository.UtilisateurRepository;
import com.min.demo.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(@RequestBody Utilisateur user) {
        try {
            authService.saveUser(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur loginUser) {
        try {
            Utilisateur user = authService.authenticateUser(loginUser.getEmail(), loginUser.getPassword());
            // Add logic for successful login
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }
}
