package com.test.test.Controller;

import com.test.test.DTO.AuthResponse;
import com.test.test.DTO.LoginRequest;
import com.test.test.DTO.RegisterRequest;
import com.test.test.Entity.Role;
import com.test.test.Entity.User;
import com.test.test.Repository.UserRepository;
import com.test.test.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        String response = userService.registerUser(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                // Get user roles and send back as part of the response
                String roles = user.getRoles().stream()
                        .map(role -> role.getName().name()) // Get the name of the enum
                        .collect(Collectors.joining(","));

                Long userId = user.getId(); // Assuming the User class has a method getId()
                return ResponseEntity.ok(new AuthResponse("Login successful!", roles, true, userId));
            }
        }
        return ResponseEntity.status(401).body(new AuthResponse("Invalid credentials", "", false, null));
    }
}
