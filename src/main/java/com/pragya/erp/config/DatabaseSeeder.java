package com.pragya.erp.config;

import com.pragya.erp.model.*;
import com.pragya.erp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired private CourseRepository courseRepository;
    @Autowired private ReportRepository reportRepository;
    @Autowired private ExamUpcomingRepository examUpcomingRepository;
    @Autowired private ExamResultRepository examResultRepository;
    @Autowired private DashboardActivityRepository dashboardActivityRepository;
    @Autowired private DashboardStatsRepository dashboardStatsRepository;
    @Autowired private FeeItemRepository feeItemRepository;
    @Autowired private LibraryBookRepository libraryBookRepository;
    @Autowired private EnrollmentRepository enrollmentRepository;
    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private MarkRepository markRepository;

    @Override
    public void run(String... args) throws Exception {
        // Courses
        if (courseRepository.count() == 0) {
            courseRepository.saveAll(List.of(
                new Course("CSE201", "Data Structures and Algorithms", 4, "Spring 2026", "Dr. Sana Qureshi", "Mon, Wed 09:40 - 11:40", "CSE Lab 2", "Active", "58/60"),
                new Course("CSE205", "Database Management Systems", 3, "Spring 2026", "Prof. Arjun Mehta", "Tue, Thu 11:50 - 13:50", "Room 214", "Active", "54/60"),
                new Course("CSE209", "Operating Systems", 3, "Spring 2026", "Dr. Fatima Noor", "Mon, Wed 14:00 - 16:00", "Room 302", "Active", "60/60"),
                new Course("CSE213", "Computer Networks", 3, "Spring 2026", "Prof. Hasan Raza", "Tue, Thu 07:30 - 09:30", "Room 108", "Active", "49/60"),
                new Course("CSE217", "Machine Learning Fundamentals", 4, "Spring 2026", "Dr. Priya Nair", "Fri 09:40 - 13:50", "AI Lab", "Active", "45/50"),
                new Course("CSE221", "Software Engineering", 3, "Spring 2026", "Prof. Omar Siddiq", "Mon 07:30 - 09:30", "Room 210", "Active", "52/60"),
                new Course("CSE223", "Human-Computer Interaction", 2, "Spring 2026", "Dr. Aisha Rahman", "Thu 14:00 - 16:00", "Design Studio", "Active", "38/45"),
                new Course("CSE225", "Cloud Computing", 3, "Spring 2026", "Prof. Imran Ali", "Wed 11:50 - 13:50", "Room 215", "Active", "41/60")
            ));
        }

        // Reports
        if (reportRepository.count() == 0) {
            reportRepository.saveAll(List.of(
                new Report("Enrollment Summary Q1 2025", "Enrollment", "2025-01-15", "completed", 1247, "PDF"),
                new Report("Department-wise Intake 2025", "Admissions", "2025-02-02", "completed", 356, "Excel"),
                new Report("Faculty Performance Report", "Academic", "2025-01-10", "completed", 89, "Excel"),
                new Report("Attendance Analysis December 2024", "Attendance", "2025-01-05", "completed", 3420, "PDF"),
                new Report("Fee Collection Report", "Financial", "2025-01-20", "pending", 0, "PDF"),
                new Report("Scholarship Utilization 2024-25", "Financial", "2025-01-28", "completed", 212, "PDF"),
                new Report("Library Circulation Summary", "Library", "2025-01-30", "completed", 486, "Excel"),
                new Report("Placement Outcomes 2024-25", "Career Services", "2025-02-05", "completed", 132, "PDF")
            ));
        }

        // Exams upcoming
        if (examUpcomingRepository.count() == 0) {
            examUpcomingRepository.saveAll(List.of(
                new ExamUpcoming("CSE201", "Data Structures Midterm", "2026-03-05", "09:00 - 11:00", "Exam Hall A", "Midterm", "Stacks, Queues, Trees"),
                new ExamUpcoming("CSE205", "DBMS Quiz 2", "2026-03-08", "11:30 - 12:30", "Room 214", "Quiz", "SQL Joins, Indexing"),
                new ExamUpcoming("CSE213", "Computer Networks Lab Test", "2026-03-12", "14:00 - 16:00", "Net Lab 1", "Lab", "Subnetting, Routing"),
                new ExamUpcoming("CSE209", "OS Midterm", "2026-03-15", "09:00 - 11:00", "Exam Hall B", "Midterm", "Process Management, Scheduling"),
                new ExamUpcoming("CSE217", "ML Assignment 2", "2026-03-20", "14:00 - 16:00", "AI Lab", "Assignment", "Neural Networks, Backpropagation")
            ));
        }

        // Exam results
        if (examResultRepository.count() == 0) {
            examResultRepository.saveAll(List.of(
                new ExamResult("CSE209", "Operating Systems Quiz 1", 18, 20, "A", "2026-02-18"),
                new ExamResult("CSE221", "Software Engineering Assignment 1", 27, 30, "A-", "2026-02-12"),
                new ExamResult("CSE217", "Machine Learning Quiz 1", 15, 20, "B+", "2026-02-09"),
                new ExamResult("CSE201", "DSA Quiz 1", 19, 20, "A+", "2026-02-05"),
                new ExamResult("CSE205", "DBMS Quiz 1", 16, 20, "A-", "2026-02-01")
            ));
        }

        // Dashboard stats
        if (dashboardStatsRepository.count() == 0) {
            dashboardStatsRepository.save(new DashboardStats(1L, 1526, 96, 14, 182));
        }

        // Dashboard activity
        if (dashboardActivityRepository.count() == 0) {
            dashboardActivityRepository.saveAll(List.of(
                new DashboardActivity("New enrollment", "Riya Sen enrolled in CSE201", "35 minutes ago"),
                new DashboardActivity("Grade submission", "Prof. Mehta submitted DBMS midterm grades", "2 hours ago"),
                new DashboardActivity("Fee payment", "74 students completed fee payment", "5 hours ago"),
                new DashboardActivity("Library issue", "12 new books issued in CSE department", "1 day ago"),
                new DashboardActivity("New course", "Cloud Computing added to catalog", "2 days ago"),
                new DashboardActivity("Attendance alert", "CSE213 attendance below 85%", "3 days ago")
            ));
        }

        // Fee Items (structure)
        if (feeItemRepository.count() == 0) {
            feeItemRepository.saveAll(List.of(
                new FeeItem("Tuition Fee", "tuition", 75000.00, "Spring 2026"),
                new FeeItem("Lab Fee", "lab", 12000.00, "Spring 2026"),
                new FeeItem("Library Fee", "library", 5000.00, "Spring 2026"),
                new FeeItem("Development Fee", "misc", 8000.00, "Spring 2026"),
                new FeeItem("Examination Fee", "misc", 3000.00, "Spring 2026"),
                new FeeItem("Sports Fee", "misc", 2000.00, "Spring 2026"),
                new FeeItem("Hostel Fee", "hostel", 45000.00, "Spring 2026"),
                new FeeItem("Mess Fee", "hostel", 30000.00, "Spring 2026")
            ));
        }

        // Library Books
        if (libraryBookRepository.count() == 0) {
            libraryBookRepository.saveAll(List.of(
                new LibraryBook("Introduction to Algorithms", "Thomas H. Cormen", "978-0262033848", "Computer Science", 5, 3),
                new LibraryBook("Database System Concepts", "Abraham Silberschatz", "978-0078022159", "Computer Science", 4, 2),
                new LibraryBook("Operating System Concepts", "Abraham Silberschatz", "978-1119800361", "Computer Science", 6, 4),
                new LibraryBook("Computer Networking: A Top-Down Approach", "James Kurose", "978-0133594140", "Computer Science", 3, 1),
                new LibraryBook("Artificial Intelligence: A Modern Approach", "Stuart Russell", "978-0134610993", "AI/ML", 4, 3),
                new LibraryBook("Pattern Recognition and Machine Learning", "Christopher Bishop", "978-0387310732", "AI/ML", 3, 2),
                new LibraryBook("Clean Code", "Robert C. Martin", "978-0132350884", "Software Engineering", 5, 5),
                new LibraryBook("Design Patterns", "Gang of Four", "978-0201633610", "Software Engineering", 3, 2),
                new LibraryBook("The Pragmatic Programmer", "David Thomas", "978-0135957059", "Software Engineering", 4, 3),
                new LibraryBook("Discrete Mathematics", "Kenneth H. Rosen", "978-0073383095", "Mathematics", 6, 5)
            ));
        }

        // Sample enrollments (student ID 1 enrolled in some courses)
        if (enrollmentRepository.count() == 0) {
            enrollmentRepository.saveAll(List.of(
                new Enrollment(1L, 1L, "2026-01-15", "active"),
                new Enrollment(1L, 2L, "2026-01-15", "active"),
                new Enrollment(1L, 3L, "2026-01-15", "active"),
                new Enrollment(1L, 5L, "2026-01-15", "active"),
                new Enrollment(1L, 6L, "2026-01-15", "active")
            ));
        }

        // Sample attendance
        if (attendanceRepository.count() == 0) {
            attendanceRepository.saveAll(List.of(
                new AttendanceRecord(1L, 1L, "2026-02-03", "present"),
                new AttendanceRecord(1L, 1L, "2026-02-05", "present"),
                new AttendanceRecord(1L, 1L, "2026-02-10", "absent"),
                new AttendanceRecord(1L, 1L, "2026-02-12", "present"),
                new AttendanceRecord(1L, 1L, "2026-02-17", "late"),
                new AttendanceRecord(1L, 2L, "2026-02-04", "present"),
                new AttendanceRecord(1L, 2L, "2026-02-06", "present"),
                new AttendanceRecord(1L, 2L, "2026-02-11", "present"),
                new AttendanceRecord(1L, 2L, "2026-02-13", "absent"),
                new AttendanceRecord(1L, 3L, "2026-02-03", "present"),
                new AttendanceRecord(1L, 3L, "2026-02-05", "present"),
                new AttendanceRecord(1L, 3L, "2026-02-10", "present")
            ));
        }

        // Sample marks
        if (markRepository.count() == 0) {
            markRepository.saveAll(List.of(
                new Mark(1L, 1L, "CSE201", "DSA Quiz 1", 19, 20, "A+"),
                new Mark(1L, 2L, "CSE205", "DBMS Quiz 1", 16, 20, "A-"),
                new Mark(1L, 3L, "CSE209", "OS Quiz 1", 18, 20, "A"),
                new Mark(1L, 4L, "CSE217", "ML Quiz 1", 15, 20, "B+"),
                new Mark(1L, 5L, "CSE221", "SE Assignment 1", 27, 30, "A-")
            ));
        }
    }
}
