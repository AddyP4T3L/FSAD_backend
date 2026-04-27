package com.pragya.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course;
    private String exam;
    private int score;
    private int total;
    private String grade;
    private String published;

    public ExamResult() {}

    public ExamResult(String course, String exam, int score, int total, String grade, String published) {
        this.course = course;
        this.exam = exam;
        this.score = score;
        this.total = total;
        this.grade = grade;
        this.published = published;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getExam() { return exam; }
    public void setExam(String exam) { this.exam = exam; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getPublished() { return published; }
    public void setPublished(String published) { this.published = published; }
}
