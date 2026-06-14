package com.fitness.fitness.controller;

import com.fitness.fitness.dto.AuthRequest;
import com.fitness.fitness.dto.AuthResponse;
import com.fitness.fitness.dto.RegisterRequest;
import com.fitness.fitness.entity.User;
import com.fitness.fitness.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        return userService.findByEmail(req.getEmail())
                .map(user -> {
                    if (user.getPassword() != null && user.getPassword().equals(req.getPassword())) {
                        AuthResponse res = new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
                        return ResponseEntity.ok(res);
                    }
                    return ResponseEntity.status(401).body("Invalid credentials");
                }).orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Email already exists");
        }
        User u = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .role(req.getRole())
                .age(req.getAge())
                .weight(req.getWeight())
                .gender(req.getGender())
                .build();
        User saved = userService.create(u);
        AuthResponse res = new AuthResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getRole());
        return ResponseEntity.ok(res);
    }
}
