package com.pragya.erp.controller;

import com.pragya.erp.model.AttendanceRecord;
import com.pragya.erp.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired private AttendanceRepository repo;

    @GetMapping
    public List<AttendanceRecord> getAll() { return repo.findAll(); }

    @GetMapping("/student/{studentId}")
    public List<AttendanceRecord> getByStudent(@PathVariable Long studentId) { return repo.findByStudentId(studentId); }

    @GetMapping("/course/{courseId}")
    public List<AttendanceRecord> getByCourse(@PathVariable Long courseId) { return repo.findByCourseId(courseId); }

    @GetMapping("/course/{courseId}/date/{date}")
    public List<AttendanceRecord> getByCourseAndDate(@PathVariable Long courseId, @PathVariable String date) {
        return repo.findByCourseIdAndDate(courseId, date);
    }

    @PostMapping
    public AttendanceRecord create(@RequestBody AttendanceRecord r) { return repo.save(r); }

    @PostMapping("/bulk")
    public List<AttendanceRecord> createBulk(@RequestBody List<AttendanceRecord> records) { return repo.saveAll(records); }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceRecord> update(@PathVariable Long id, @RequestBody AttendanceRecord r) {
        return repo.findById(id).map(existing -> {
            existing.setStatus(r.getStatus());
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
