package com.pragya.erp.controller;

import com.pragya.erp.model.Enrollment;
import com.pragya.erp.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    @Autowired private EnrollmentRepository repo;

    @GetMapping
    public List<Enrollment> getAll() { return repo.findAll(); }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getByStudent(@PathVariable Long studentId) { return repo.findByStudentId(studentId); }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> getByCourse(@PathVariable Long courseId) { return repo.findByCourseId(courseId); }

    @PostMapping
    public Enrollment create(@RequestBody Enrollment e) {
        if (e.getEnrolledAt() == null) e.setEnrolledAt(java.time.LocalDate.now().toString());
        if (e.getStatus() == null) e.setStatus("active");
        return repo.save(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> update(@PathVariable Long id, @RequestBody Enrollment e) {
        return repo.findById(id).map(existing -> {
            existing.setStatus(e.getStatus());
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
