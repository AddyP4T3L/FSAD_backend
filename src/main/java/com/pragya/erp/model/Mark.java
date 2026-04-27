package com.pragya.erp.model;

import jakarta.persistence.*;

@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long examId;
    private String courseName;
    private String examName;
    private int score;
    private int total;
    private String grade;

    public Mark() {}
    public Mark(Long studentId, Long examId, String courseName, String examName, int score, int total, String grade) {
        this.studentId = studentId; this.examId = examId; this.courseName = courseName;
        this.examName = examName; this.score = score; this.total = total; this.grade = grade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getExamId() { return examId; }
    public void setExamId(Long examId) { this.examId = examId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
