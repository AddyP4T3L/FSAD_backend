package com.pragya.erp.repository;

import com.pragya.erp.model.ExamUpcoming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamUpcomingRepository extends JpaRepository<ExamUpcoming, Long> {
}
