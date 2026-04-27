package com.pragya.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ExamUpcoming {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String course;
    private String title;
    private String date;
    private String time;
    private String room;
    private String type;
    private String syllabus;

    public ExamUpcoming() {}

    public ExamUpcoming(String course, String title, String date, String time, String room, String type, String syllabus) {
        this.course = course;
        this.title = title;
        this.date = date;
        this.time = time;
        this.room = room;
        this.type = type;
        this.syllabus = syllabus;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSyllabus() { return syllabus; }
    public void setSyllabus(String syllabus) { this.syllabus = syllabus; }
}
