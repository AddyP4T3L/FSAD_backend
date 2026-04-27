package com.pragya.erp.controller;

import com.pragya.erp.model.Course;
import com.pragya.erp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseRepository.findById(id).map(existing -> {
            existing.setCode(course.getCode());
            existing.setTitle(course.getTitle());
            existing.setCredits(course.getCredits());
            existing.setSemester(course.getSemester());
            existing.setInstructor(course.getInstructor());
            existing.setSchedule(course.getSchedule());
            existing.setRoom(course.getRoom());
            existing.setStatus(course.getStatus());
            existing.setSeats(course.getSeats());
            return ResponseEntity.ok(courseRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (!courseRepository.existsById(id)) return ResponseEntity.notFound().build();
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
