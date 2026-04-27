package com.pragya.erp.model;

import jakarta.persistence.*;

@Entity
public class LibraryIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long bookId;
    private String issuedAt;
    private String dueDate;
    private String returnedAt;
    private String status; // issued, returned, overdue

    public LibraryIssue() {}
    public LibraryIssue(Long studentId, Long bookId, String issuedAt, String dueDate, String returnedAt, String status) {
        this.studentId = studentId; this.bookId = bookId; this.issuedAt = issuedAt;
        this.dueDate = dueDate; this.returnedAt = returnedAt; this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getIssuedAt() { return issuedAt; }
    public void setIssuedAt(String issuedAt) { this.issuedAt = issuedAt; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public String getReturnedAt() { return returnedAt; }
    public void setReturnedAt(String returnedAt) { this.returnedAt = returnedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
