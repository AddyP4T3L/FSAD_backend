package com.pragya.erp.repository;
import com.pragya.erp.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByStudentId(Long studentId);
    List<AttendanceRecord> findByCourseId(Long courseId);
    List<AttendanceRecord> findByCourseIdAndDate(Long courseId, String date);
    List<AttendanceRecord> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
