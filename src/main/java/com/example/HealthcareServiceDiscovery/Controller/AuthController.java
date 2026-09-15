package com.example.HealthcareServiceDiscovery.Controller;

import com.example.HealthcareServiceDiscovery.DTO.UserResponseDTO;
import com.example.HealthcareServiceDiscovery.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO registerUser(
            @RequestParam String username,
            @RequestParam String password) {

        return userService.registerUser(
                username,
                password
        );
    }
}