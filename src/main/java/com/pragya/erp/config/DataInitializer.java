package com.pragya.erp.config;

import com.pragya.erp.model.User;
import com.pragya.erp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Base64;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String hashPassword(String rawPassword, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashed = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashed);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
                // Common demo password for all seeded accounts
                String demoPassword = "1234";

                User student = new User(
                        "Pragya",
                        "student@campuscore.edu",
                        "Student",
                        "Computer Science",
                        "active",
                        LocalDate.of(2023, 9, 1)
                );
                student.setUsername("student");
                String studentSalt = generateSalt();
                student.setPasswordSalt(studentSalt);
                student.setPasswordHash(hashPassword(demoPassword, studentSalt));
                userRepository.save(student);

                User teacher = new User(
                        "Rahul Sharma",
                        "teacher@campuscore.edu",
                        "Teacher",
                        "Mathematics",
                        "active",
                        LocalDate.of(2019, 8, 15)
                );
                teacher.setUsername("teacher");
                String teacherSalt = generateSalt();
                teacher.setPasswordSalt(teacherSalt);
                teacher.setPasswordHash(hashPassword(demoPassword, teacherSalt));
                userRepository.save(teacher);
        }

            // Ensure Admin exists
            boolean adminExists = false;
            for (User u : userRepository.findAll()) {
                if ("admin@campuscore.edu".equals(u.getEmail())) {
                    adminExists = true;
                    break;
                }
            }

        if (!adminExists) {
            String demoPassword = "1234";
            User admin = new User(
                    "Admin CampusCore",
                    "admin@campuscore.edu",
                    "Admin",
                    "Administration",
                    "active",
                    LocalDate.of(2018, 1, 1)
            );
            admin.setUsername("admin");
            String adminSalt = generateSalt();
            admin.setPasswordSalt(adminSalt);
            admin.setPasswordHash(hashPassword(demoPassword, adminSalt));
            userRepository.save(admin);
        }
    }
}
