package com.pragya.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DashboardStats {
    @Id
    private Long id; // We will only use id 1
    private int students;
    private int teachers;
    private int departments;
    private int courses;

    public DashboardStats() {}

    public DashboardStats(Long id, int students, int teachers, int departments, int courses) {
        this.id = id;
        this.students = students;
        this.teachers = teachers;
        this.departments = departments;
        this.courses = courses;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getStudents() { return students; }
    public void setStudents(int students) { this.students = students; }
    public int getTeachers() { return teachers; }
    public void setTeachers(int teachers) { this.teachers = teachers; }
    public int getDepartments() { return departments; }
    public void setDepartments(int departments) { this.departments = departments; }
    public int getCourses() { return courses; }
    public void setCourses(int courses) { this.courses = courses; }
}
