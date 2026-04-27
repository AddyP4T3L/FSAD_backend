package com.pragya.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.pragya.erp.repository.*;

@RestController
@RequestMapping("/api/database")
public class DatabaseViewController {
    @Autowired private com.pragya.erp.repository.UserRepository userRepo;
    @Autowired private CourseRepository courseRepo;
    @Autowired private EnrollmentRepository enrollmentRepo;
    @Autowired private AttendanceRepository attendanceRepo;
    @Autowired private MarkRepository markRepo;
    @Autowired private ExamUpcomingRepository examUpcomingRepo;
    @Autowired private ExamResultRepository examResultRepo;
    @Autowired private FeeItemRepository feeItemRepo;
    @Autowired private FeePaymentRepository feePaymentRepo;
    @Autowired private LibraryBookRepository libraryBookRepo;
    @Autowired private LibraryIssueRepository libraryIssueRepo;
    @Autowired private ReportRepository reportRepo;
    @Autowired private DashboardStatsRepository dashboardStatsRepo;
    @Autowired private DashboardActivityRepository dashboardActivityRepo;

    @GetMapping
    public Map<String, Object> getDatabaseView() {
        Map<String, Object> db = new LinkedHashMap<>();
        db.put("users", userRepo.findAll());
        db.put("courses", courseRepo.findAll());
        db.put("enrollments", enrollmentRepo.findAll());
        db.put("attendance", attendanceRepo.findAll());
        db.put("marks", markRepo.findAll());
        db.put("examsUpcoming", examUpcomingRepo.findAll());
        db.put("examResults", examResultRepo.findAll());
        db.put("feeItems", feeItemRepo.findAll());
        db.put("feePayments", feePaymentRepo.findAll());
        db.put("libraryBooks", libraryBookRepo.findAll());
        db.put("libraryIssues", libraryIssueRepo.findAll());
        db.put("reports", reportRepo.findAll());
        db.put("dashboardStats", dashboardStatsRepo.findAll());
        db.put("dashboardActivity", dashboardActivityRepo.findAll());
        return db;
    }

    @GetMapping("/stats")
    public Map<String, Long> getTableCounts() {
        Map<String, Long> counts = new LinkedHashMap<>();
        counts.put("users", userRepo.count());
        counts.put("courses", courseRepo.count());
        counts.put("enrollments", enrollmentRepo.count());
        counts.put("attendance", attendanceRepo.count());
        counts.put("marks", markRepo.count());
        counts.put("examsUpcoming", examUpcomingRepo.count());
        counts.put("examResults", examResultRepo.count());
        counts.put("feeItems", feeItemRepo.count());
        counts.put("feePayments", feePaymentRepo.count());
        counts.put("libraryBooks", libraryBookRepo.count());
        counts.put("libraryIssues", libraryIssueRepo.count());
        counts.put("reports", reportRepo.count());
        return counts;
    }
}
