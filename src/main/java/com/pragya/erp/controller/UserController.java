package com.pragya.erp.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragya.erp.model.User;
import com.pragya.erp.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        try {
            String defaultPassword = "1234";
            String salt = java.util.UUID.randomUUID().toString();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hashed = md.digest(defaultPassword.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getEncoder().encodeToString(hashed);
            user.setPasswordSalt(salt);
            user.setPasswordHash(hash);
            if (user.getDepartment() == null || user.getDepartment().isBlank()) {
                user.setDepartment("Computer Science");
            }
            if (user.getStatus() == null || user.getStatus().isBlank()) {
                user.setStatus("active");
            }
            user.setPasswordChangeRequired(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updated) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setEmail(updated.getEmail());
                    existing.setRole(updated.getRole());
                    existing.setDepartment(updated.getDepartment());
                    existing.setStatus(updated.getStatus());
                    existing.setJoinedAt(updated.getJoinedAt());
                    return ResponseEntity.ok(userRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody java.util.Map<String, String> request) {
        return userRepository.findById(id).map(user -> {
            try {
                String newPassword = request.get("newPassword");
                String salt = java.util.UUID.randomUUID().toString();
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(salt.getBytes(StandardCharsets.UTF_8));
                byte[] hashed = md.digest(newPassword.getBytes(StandardCharsets.UTF_8));
                String hash = Base64.getEncoder().encodeToString(hashed);
                user.setPasswordSalt(salt);
                user.setPasswordHash(hash);
                user.setPasswordChangeRequired(false);
                userRepository.save(user);
                return ResponseEntity.ok(java.util.Map.of("message", "Password updated successfully"));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }
}
