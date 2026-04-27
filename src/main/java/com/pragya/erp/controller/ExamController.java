package com.pragya.erp.controller;

import com.pragya.erp.model.ExamUpcoming;
import com.pragya.erp.model.ExamResult;
import com.pragya.erp.repository.ExamUpcomingRepository;
import com.pragya.erp.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamUpcomingRepository upcomingRepository;
    
    @Autowired
    private ExamResultRepository resultRepository;

    public record ExamResponse(List<ExamUpcoming> upcoming, List<ExamResult> results) {}

    @GetMapping
    public ExamResponse getExams() {
        return new ExamResponse(upcomingRepository.findAll(), resultRepository.findAll());
    }
}
