package com.pragya.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String title;
    private int credits;
    private String semester;
    private String instructor;
    private String schedule;
    private String room;
    private String status;
    private String seats;

    public Course() {}

    public Course(String code, String title, int credits, String semester, String instructor, String schedule, String room, String status, String seats) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.semester = semester;
        this.instructor = instructor;
        this.schedule = schedule;
        this.room = room;
        this.status = status;
        this.seats = seats;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSeats() { return seats; }
    public void setSeats(String seats) { this.seats = seats; }
}
