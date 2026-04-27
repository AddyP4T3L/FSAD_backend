package com.pragya.erp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String type;
    private String generatedAt;
    private String status;
    private int records;
    private String format;

    public Report() {}

    public Report(String title, String type, String generatedAt, String status, int records, String format) {
        this.title = title;
        this.type = type;
        this.generatedAt = generatedAt;
        this.status = status;
        this.records = records;
        this.format = format;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(String generatedAt) { this.generatedAt = generatedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getRecords() { return records; }
    public void setRecords(int records) { this.records = records; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
}
