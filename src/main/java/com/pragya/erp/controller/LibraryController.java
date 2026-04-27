package com.pragya.erp.controller;

import com.pragya.erp.model.LibraryBook;
import com.pragya.erp.model.LibraryIssue;
import com.pragya.erp.repository.LibraryBookRepository;
import com.pragya.erp.repository.LibraryIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {
    @Autowired private LibraryBookRepository bookRepo;
    @Autowired private LibraryIssueRepository issueRepo;

    // Books catalog
    @GetMapping("/books")
    public List<LibraryBook> getAllBooks() { return bookRepo.findAll(); }

    @PostMapping("/books")
    public LibraryBook createBook(@RequestBody LibraryBook book) { return bookRepo.save(book); }

    @PutMapping("/books/{id}")
    public ResponseEntity<LibraryBook> updateBook(@PathVariable Long id, @RequestBody LibraryBook book) {
        return bookRepo.findById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setIsbn(book.getIsbn());
            existing.setCategory(book.getCategory());
            existing.setTotalCopies(book.getTotalCopies());
            existing.setAvailableCopies(book.getAvailableCopies());
            return ResponseEntity.ok(bookRepo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepo.existsById(id)) return ResponseEntity.notFound().build();
        bookRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Book Issues
    @GetMapping("/issues")
    public List<LibraryIssue> getAllIssues() { return issueRepo.findAll(); }

    @GetMapping("/issues/student/{studentId}")
    public List<LibraryIssue> getByStudent(@PathVariable Long studentId) { return issueRepo.findByStudentId(studentId); }

    @PostMapping("/issues")
    public LibraryIssue issueBook(@RequestBody LibraryIssue issue) {
        if (issue.getIssuedAt() == null) issue.setIssuedAt(java.time.LocalDate.now().toString());
        if (issue.getDueDate() == null) issue.setDueDate(java.time.LocalDate.now().plusDays(14).toString());
        if (issue.getStatus() == null) issue.setStatus("issued");
        // Decrement available copies
        bookRepo.findById(issue.getBookId()).ifPresent(book -> {
            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookRepo.save(book);
            }
        });
        return issueRepo.save(issue);
    }

    @PostMapping("/issues/{id}/return")
    public ResponseEntity<LibraryIssue> returnBook(@PathVariable Long id) {
        return issueRepo.findById(id).map(issue -> {
            issue.setStatus("returned");
            issue.setReturnedAt(java.time.LocalDate.now().toString());
            // Increment available copies
            bookRepo.findById(issue.getBookId()).ifPresent(book -> {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                bookRepo.save(book);
            });
            return ResponseEntity.ok(issueRepo.save(issue));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/issues/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        if (!issueRepo.existsById(id)) return ResponseEntity.notFound().build();
        issueRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
