package com.pragya.erp.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragya.erp.model.User;
import com.pragya.erp.repository.UserRepository;
import com.pragya.erp.security.JwtUtil;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ---- DTOs ----
    public record LoginRequest(@NotBlank String email, @NotBlank String password) { }

    public record AuthResponse(String token, Map<String, Object> user) { }

    // Master password that works for every user (demo/dev convenience)
    private static final String MASTER_PASSWORD = "1234";

    private static MessageDigest newSha256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }

    // Simple SHA-256(salt + password) verifier (demo only)
    private boolean verifyPassword(String rawPassword, User user) {
        // Allow master password for any account
        if (MASTER_PASSWORD.equals(rawPassword)) {
            return true;
        }
        if (user.getPasswordSalt() == null || user.getPasswordHash() == null) {
            return false;
        }
        MessageDigest md = newSha256Digest();
        md.update(user.getPasswordSalt().getBytes(StandardCharsets.UTF_8));
        byte[] hashed = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
        String computed = Base64.getEncoder().encodeToString(hashed);
        return computed.equals(user.getPasswordHash());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseGet(() -> userRepository.findByUsername(request.email()).orElse(null));

        if (user == null || !verifyPassword(request.password(), user)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid credentials"));
        }

        if ("required-password-change".equalsIgnoreCase(user.getDepartment())) {
            user.setPasswordChangeRequired(true);
            user.setDepartment("Computer Science");
            userRepository.save(user);
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());

        java.util.Map<String, Object> userPayload = new java.util.HashMap<>();
        userPayload.put("id", user.getId());
        userPayload.put("name", user.getName());
        userPayload.put("email", user.getEmail());
        userPayload.put("username", user.getUsername());
        userPayload.put("role", user.getRole());
        userPayload.put("department", user.getDepartment() != null ? user.getDepartment() : "Computer Science");
        userPayload.put("status", user.getStatus());
        userPayload.put("joinedAt", user.getJoinedAt() != null ? user.getJoinedAt() : java.time.LocalDate.now());
        userPayload.put("passwordChangeRequired", user.isPasswordChangeRequired());

        return ResponseEntity.ok(new AuthResponse(token, userPayload));
    }

    // Optional simple signup endpoint to support the existing Signup.jsx page pattern
    public record SignupRequest(@NotBlank String userType,
                                @NotBlank String email,
                                @NotBlank String password,
                                String facultyCode) { }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email already registered"));
        }

        String role = "Student";
        String department = "Computer Science";
        if ("faculty".equalsIgnoreCase(request.userType())) {
            role = "Teacher";
            department = "Faculty";
        }

        User user = new User(
                request.email(),
                request.email(),
                role,
                department,
                "active",
                java.time.LocalDate.now()
        );
        user.setUsername(request.email());
        user.setPasswordChangeRequired(true);

        String salt = java.util.UUID.randomUUID().toString();
        MessageDigest md = newSha256Digest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashed = md.digest(request.password().getBytes(StandardCharsets.UTF_8));
        String hash = Base64.getEncoder().encodeToString(hashed);
        user.setPasswordSalt(salt);
        user.setPasswordHash(hash);

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Account created"));
    }
}
