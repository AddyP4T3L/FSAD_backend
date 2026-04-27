package com.pragya.erp.model;

import jakarta.persistence.*;

@Entity
public class FeePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private Long feeItemId;
    private double amountPaid;
    private String paidAt;
    private String method; // online, cash, cheque
    private String transactionId;
    private String status; // paid, pending

    public FeePayment() {}
    public FeePayment(Long studentId, Long feeItemId, double amountPaid, String paidAt, String method, String transactionId, String status) {
        this.studentId = studentId; this.feeItemId = feeItemId; this.amountPaid = amountPaid;
        this.paidAt = paidAt; this.method = method; this.transactionId = transactionId; this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getFeeItemId() { return feeItemId; }
    public void setFeeItemId(Long feeItemId) { this.feeItemId = feeItemId; }
    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
    public String getPaidAt() { return paidAt; }
    public void setPaidAt(String paidAt) { this.paidAt = paidAt; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
