package com.pragya.erp.repository;
import com.pragya.erp.model.LibraryIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LibraryIssueRepository extends JpaRepository<LibraryIssue, Long> {
    List<LibraryIssue> findByStudentId(Long studentId);
    List<LibraryIssue> findByBookId(Long bookId);
    List<LibraryIssue> findByStatus(String status);
}
