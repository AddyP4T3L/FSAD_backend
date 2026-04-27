package com.pragya.erp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragya.erp.model.FeeItem;
import com.pragya.erp.model.FeePayment;
import com.pragya.erp.repository.FeeItemRepository;
import com.pragya.erp.repository.FeePaymentRepository;

@RestController
@RequestMapping("/api/fees")
public class FeeController {
    @Autowired private FeeItemRepository feeItemRepo;
    @Autowired private FeePaymentRepository feePaymentRepo;

    // Fee Items (structure)
    @GetMapping("/items")
    public List<FeeItem> getAllItems() { return feeItemRepo.findAll(); }

    @PostMapping("/items")
    public FeeItem createItem(@RequestBody FeeItem item) { return feeItemRepo.save(item); }

    @PutMapping("/items/{id}")
    public ResponseEntity<FeeItem> updateItem(@PathVariable Long id, @RequestBody FeeItem item) {
        return feeItemRepo.findById(id).map(existing -> {
            existing.setName(item.getName());
            existing.setCategory(item.getCategory());
            existing.setAmount(item.getAmount());
            existing.setSemester(item.getSemester());
            return ResponseEntity.ok(feeItemRepo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        if (!feeItemRepo.existsById(id)) return ResponseEntity.notFound().build();
        feeItemRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Fee Payments
    @GetMapping("/payments")
    public List<FeePayment> getAllPayments() { return feePaymentRepo.findAll(); }

    @GetMapping("/payments/student/{studentId}")
    public List<FeePayment> getByStudent(@PathVariable Long studentId) { return feePaymentRepo.findByStudentId(studentId); }

    @PostMapping("/payments")
    public FeePayment createPayment(@RequestBody FeePayment payment) {
        if (payment.getPaidAt() == null) payment.setPaidAt(java.time.LocalDate.now().toString());
        if (payment.getStatus() == null) payment.setStatus("paid");
        if (payment.getTransactionId() == null) payment.setTransactionId("TXN" + System.currentTimeMillis());
        return feePaymentRepo.save(payment);
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<FeePayment> updatePayment(@PathVariable Long id, @RequestBody FeePayment payment) {
        return feePaymentRepo.findById(id).map(existing -> {
            if (payment.getAmountPaid() != 0) existing.setAmountPaid(payment.getAmountPaid());
            if (payment.getFeeItemId() != null) existing.setFeeItemId(payment.getFeeItemId());
            if (payment.getPaidAt() != null) existing.setPaidAt(payment.getPaidAt());
            if (payment.getMethod() != null) existing.setMethod(payment.getMethod());
            if (payment.getTransactionId() != null) existing.setTransactionId(payment.getTransactionId());
            if (payment.getStatus() != null) existing.setStatus(payment.getStatus());
            return ResponseEntity.ok(feePaymentRepo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/payments/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        if (!feePaymentRepo.existsById(id)) return ResponseEntity.notFound().build();
        feePaymentRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
