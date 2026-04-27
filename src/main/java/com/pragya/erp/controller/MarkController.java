package com.pragya.erp.controller;

import com.pragya.erp.model.Mark;
import com.pragya.erp.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarkController {
    @Autowired private MarkRepository repo;

    @GetMapping
    public List<Mark> getAll() { return repo.findAll(); }

    @GetMapping("/student/{studentId}")
    public List<Mark> getByStudent(@PathVariable Long studentId) { return repo.findByStudentId(studentId); }

    @GetMapping("/exam/{examId}")
    public List<Mark> getByExam(@PathVariable Long examId) { return repo.findByExamId(examId); }

    @PostMapping
    public Mark create(@RequestBody Mark m) { return repo.save(m); }

    @PostMapping("/bulk")
    public List<Mark> createBulk(@RequestBody List<Mark> marks) { return repo.saveAll(marks); }

    @PutMapping("/{id}")
    public ResponseEntity<Mark> update(@PathVariable Long id, @RequestBody Mark m) {
        return repo.findById(id).map(existing -> {
            existing.setScore(m.getScore());
            existing.setTotal(m.getTotal());
            existing.setGrade(m.getGrade());
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
