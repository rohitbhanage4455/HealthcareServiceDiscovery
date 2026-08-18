package com.example.HealthcareServiceDiscovery.Service;

import com.example.HealthcareServiceDiscovery.Entity.User;
import com.example.HealthcareServiceDiscovery.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(
            String username,
            String password,
            String role) {

        User user = new User();

        user.setUsername(username);
        user.setPassword(
                passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }
}