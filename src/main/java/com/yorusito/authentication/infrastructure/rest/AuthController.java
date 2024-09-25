package com.yorusito.authentication.infrastructure.rest;

import com.yorusito.authentication.domain.model.User;
import com.yorusito.authentication.domain.service.UserService;
import com.yorusito.authentication.infrastructure.rest.dto.LoginRequest;
import com.yorusito.authentication.infrastructure.rest.dto.RegisterRequest;
import com.yorusito.authentication.infrastructure.rest.dto.UserDTO;
import com.yorusito.authentication.infrastructure.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("ROLE_USER");  // Establecer el rol predeterminado como "USER"

        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User foundUser = userService.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())) {
            String token = jwtTokenProvider.generateToken(foundUser.getUsername(), List.of(foundUser.getRole()));
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInfo(@RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsernameFromToken(token.substring(7));  // Remover el prefijo "Bearer "
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());

        return ResponseEntity.ok(userDTO);
    }
}
